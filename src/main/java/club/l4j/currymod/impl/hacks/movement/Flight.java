package club.l4j.currymod.impl.hacks.movement;

import club.l4j.currymod.core.event.events.PacketSendEvent;
import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.hack.options.impl.OptionMode;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import club.l4j.currymod.mixin.minecraft.IPlayerMoveC2SPacket;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Hack.Construct(name = "Flight", description = "fly hacky", category = Hack.Category.MOVEMENT)
public class Flight extends Hack {

    public OptionBoolean antiKick = new OptionBoolean("AntiKick", true);
    public OptionMode mode = new OptionMode("Mode", "Creative", "Creative", "JetPack", "AirHop");
    public OptionSlider speed = new OptionSlider("Speed", 1f, 10f, 1f, 5f);

    //AirHop
    double startY = 0;
    int timeout = 0;

    //Antikick
    double lastY = Double.MAX_VALUE;
    long lastTime;


    public Flight() {
        addOptions(antiKick, mode, speed);
    }

    @Override
    public void onEnable() {
        if (mode.isMode("Creative")) {
            if(mc.player.isOnGround()){
                mc.player.jump();
            }
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(speed.getFloatValue() / 10f);
        }
        startY = mc.player.getY();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if(!mc.player.isCreative()) {
            mc.player.getAbilities().allowFlying = false;
        }
        mc.player.getAbilities().flying = false;
        mc.player.getAbilities().setFlySpeed(0.05f);
        super.onDisable();
    }

    @DemoListen
    public void onTick(TickEvent e){
        if(mode.isMode("JetPack") && mc.options.jumpKey.isPressed()){
            mc.player.jump();
        } else if (mode.isMode("AirHop")) {
            timeout--;
            if(timeout < 0){
                timeout = 0;
            }
            if(mc.player.getY() < startY && timeout == 0) {
                mc.player.jump();
            }
        }
    }

    @DemoListen
    public void onPacketSend(PacketSendEvent e) {
        if (antiKick.isEnabled() && !mode.isMode("AirHop") && e.getPacket() instanceof PlayerMoveC2SPacket p) {
            long time = System.currentTimeMillis();
            double y = p.getY(Double.MAX_VALUE);
            if (y != Double.MAX_VALUE) {
                if (time - lastTime > 1000 && lastY != Double.MAX_VALUE && mc.world.getBlockState(mc.player.getBlockPos().down()).isAir()) {
                    ((IPlayerMoveC2SPacket) p).setY(lastY - 0.03130D);
                    lastTime = time;
                } else {
                    lastY = y;
                }
            }
        }

    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }
}
