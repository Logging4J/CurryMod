package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.PacketReceiveEvent;
import club.l4j.currymod.event.events.PacketSendEvent;
import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import club.l4j.currymod.mixin.minecraft.IPlayerMoveC2SPacket;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

@Hack.Construct(name = "Flight", description = "fly hacky", category = Hack.Category.MOVEMENT)
public class Flight extends Hack {

    public OptionBoolean antiKick = new OptionBoolean("AntiKick", true);
    public OptionMode mode = new OptionMode("Mode", "Creative", "Creative", "JetPack", "VerusHop");
    OptionSlider speed = new OptionSlider("Speed", 1f, 10f, 1f, 5f);

    //VerusHop
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
        } else if (mode.isMode("VerusHop")) {
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
        if(antiKick.isEnabled() && !mode.isMode("VerusHop") && e.getPacket() instanceof PlayerMoveC2SPacket packet){
            long time = System.currentTimeMillis();
            double y = packet.getY(Double.MAX_VALUE);
            if (y != Double.MAX_VALUE) {
                if (time - lastTime > 1000 && lastY != Double.MAX_VALUE && mc.world.getBlockState(mc.player.getBlockPos().down()).isAir()) {
                    ((IPlayerMoveC2SPacket) packet).setY(lastY - 0.03130D);
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
