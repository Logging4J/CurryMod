package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.PacketReceiveEvent;
import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import club.l4j.currymod.mixin.minecraft.IEntityVelocityUpdateS2CPacket;
import club.l4j.currymod.mixin.minecraft.IExplosionS2CPacket;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.c2s.play.PlayPongC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

@Hack.Construct(name = "Velocity", description = "kb", category = Hack.Category.COMBAT)
public class Velocity extends Hack {

    public OptionMode mode = new OptionMode("Mode","Cancel","Cancel", "Modify","GrimReduce");
    public OptionSlider horizontalVal =  new OptionSlider("ModifyH",1,100,1,100);
    public OptionSlider verticalVal =  new OptionSlider("ModifyV",1,100,1,100);

    public Velocity(){
        addOptions(mode, horizontalVal, verticalVal);
    }

    @DemoListen
    public void onPacketReceive(PacketReceiveEvent e) {
        if(e.getPacket() instanceof ExplosionS2CPacket packet){
            if(mode.isMode("Cancel")){
                ((IExplosionS2CPacket) packet).setVelocityX(0.0f);
                ((IExplosionS2CPacket) packet).setVelocityY(0.0f);
                ((IExplosionS2CPacket) packet).setVelocityZ(0.0f);
            }else if(mode.isMode("Modify")){
                ((IExplosionS2CPacket) packet).setVelocityX(packet.getPlayerVelocityX() * (horizontalVal.getIntValue() / 100.0f));
                ((IExplosionS2CPacket) packet).setVelocityY(packet.getPlayerVelocityY() * (verticalVal.getIntValue() / 100.0f));
                ((IExplosionS2CPacket) packet).setVelocityZ(packet.getPlayerVelocityZ() * (horizontalVal.getIntValue() / 100.0f));

            }
        } else if (e.getPacket() instanceof EntityVelocityUpdateS2CPacket packet && packet.getId() == mc.player.getId()) {
            if(mode.isMode("Cancel")){
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX(0);
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityY(0);
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityZ(0);
            }else if(mode.isMode("Modify")){
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX((int) (((packet.getVelocityX() / 8000d - mc.player.getVelocity().x) * (horizontalVal.getIntValue() / 100)) * 8000 + mc.player.getVelocity().x * 8000));
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityY((int) (((packet.getVelocityY() / 8000d - mc.player.getVelocity().y) * (verticalVal.getIntValue() / 100)) * 8000 + mc.player.getVelocity().y * 8000));
                ((IEntityVelocityUpdateS2CPacket) packet).setVelocityY((int) (((packet.getVelocityZ() / 8000d - mc.player.getVelocity().z) * (horizontalVal.getIntValue() / 100)) * 8000 + mc.player.getVelocity().z * 8000));
            //Simple reduce velocity for servers that Run GrimAC
            } else if (mode.isMode("GrimReduce")) {
                if(mc.player.hurtTime >0) {
                    if(mc.player.isOnGround()) {
                        if(mc.player.hurtTime <= 6) {
                            ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX((int) (packet.getVelocityX() * 0.7D));
                            ((IEntityVelocityUpdateS2CPacket) packet).setVelocityZ((int) (packet.getVelocityZ() * 0.7D));
                        }
                        if(mc.player.hurtTime <= 5) {
                            ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX((int) (packet.getVelocityX() * 0.8D));
                            ((IEntityVelocityUpdateS2CPacket) packet).setVelocityZ((int) (packet.getVelocityZ() * 0.8D));
                        }
                    } else if (mc.player.hurtTime <= 10) {
                        ((IEntityVelocityUpdateS2CPacket) packet).setVelocityX((int) (packet.getVelocityX() * 0.6D));
                        ((IEntityVelocityUpdateS2CPacket) packet).setVelocityZ((int) (packet.getVelocityZ() * 0.6D));
                    }
                }
            }
        }
    }

    @Override
    public String getContent() {
        return "["+mode.getMode()+"]";
    }
}
