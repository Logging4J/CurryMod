package club.l4j.currymod.impl.hacks.combat;

import club.l4j.currymod.core.event.events.PacketReceiveEvent;
import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.hack.options.impl.OptionMode;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import club.l4j.currymod.mixin.minecraft.IEntityVelocityUpdateS2CPacket;
import club.l4j.currymod.mixin.minecraft.IExplosionS2CPacket;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

@Hack.Construct(name = "Velocity", description = "kb", category = Hack.Category.COMBAT)
public class Velocity extends Hack {

    public OptionMode mode = new OptionMode("Mode","Cancel","Cancel", "Modify","Grim");
    public OptionSlider horizontalVal =  new OptionSlider("ModifyH",1,100,1,100);
    public OptionSlider verticalVal =  new OptionSlider("ModifyV",1,100,1,100);
    public static OptionBoolean noPush = new OptionBoolean("NoPush", true);
    public static OptionBoolean noWaterVel = new OptionBoolean("NoWaterVel", true);


    public Velocity(){
        addOptions(mode, horizontalVal, verticalVal, noPush, noWaterVel);
    }

    @DemoListen
    public void onPacketReceive(PacketReceiveEvent e) {
        if(nullCheck()) return;
        if(e.getPacket() instanceof ExplosionS2CPacket packet){
            if(mode.isMode("Cancel")){
                e.setCanceled(true);
            }else if(mode.isMode("Modify")){
                ((IExplosionS2CPacket) packet).setVelocityX(packet.getPlayerVelocityX() * (horizontalVal.getIntValue() / 100.0f));
                ((IExplosionS2CPacket) packet).setVelocityY(packet.getPlayerVelocityY() * (verticalVal.getIntValue() / 100.0f));
                ((IExplosionS2CPacket) packet).setVelocityZ(packet.getPlayerVelocityZ() * (horizontalVal.getIntValue() / 100.0f));
            }
        } else if (e.getPacket() instanceof EntityVelocityUpdateS2CPacket packet && packet.getId() == mc.player.getId()) {
            if(mode.isMode("Cancel")){
                e.setCanceled(true);
            }else if(mode.isMode("Modify")){
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX((int) (((packet.getVelocityX() / 8000d - mc.player.getVelocity().x) * (horizontalVal.getIntValue() / 100)) * 8000 + mc.player.getVelocity().x * 8000));
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityY((int) (((packet.getVelocityY() / 8000d - mc.player.getVelocity().y) * (verticalVal.getIntValue() / 100)) * 8000 + mc.player.getVelocity().y * 8000));
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityY((int) (((packet.getVelocityZ() / 8000d - mc.player.getVelocity().z) * (horizontalVal.getIntValue() / 100)) * 8000 + mc.player.getVelocity().z * 8000));
            } else if (mode.isMode("Grim")) {
                e.setCanceled(true);
            }
        }
    }

    @DemoListen
    public void onTick(TickEvent e){

    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }
}
