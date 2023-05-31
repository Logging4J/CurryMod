package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.PacketSendEvent;
import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import club.l4j.currymod.mixin.minecraft.IPlayerMoveC2SPacket;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Hack.Construct(name = "Aura", description = "attacks ppl fo u", category = Hack.Category.COMBAT)
public class Aura extends Hack {

    OptionSlider range = new OptionSlider("Range",1,6,1,6);

    public Aura(){
        addOptions(range);
    }

    float yaw, pitch;
    int delay;

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        if(mc.player.getAttackCooldownProgress(0) == 1){
            delay++;
            if(delay >= 20){
                List<PlayerEntity> closestPlayer = mc.world.getPlayers().stream().filter(player -> player.distanceTo(mc.player) < range.getIntValue() && player != mc.player && player.isAlive()).collect(Collectors.toList());
                closestPlayer.sort(Comparator.comparingDouble(player -> player.distanceTo(mc.player)));
                if(!closestPlayer.isEmpty()) {
                    PlayerEntity p = closestPlayer.get(0);
                    yaw = getRotations(p)[0];
                    pitch = getRotations(p)[1];
                    mc.interactionManager.attackEntity(mc.player, p);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }

    public float[] getRotations(Entity e) {
        double deltaX = e.getX() + (e.getX() - e.prevX) - mc.player.getX();
        double deltaY = e.getY() - 3.5 + e.getEyeHeight(e.getPose()) - mc.player.getY() + mc.player.getEyeHeight(e.getPose());
        double deltaZ = e.getZ() + (e.getZ() - e.prevZ) - mc.player.getZ();
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ));
        float pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
        double v = Math.toDegrees(Math.atan(deltaZ / deltaX));
        if (deltaX < 0 && deltaZ < 0) {
            yaw = (float) (90 + v);
        } else if (deltaX > 0 && deltaZ < 0) {
            yaw = (float) (-90 + v);
        }
        return new float[] {yaw, pitch};
    }

    @DemoListen
    public void onPacketSend(PacketSendEvent e) {
        if (nullCheck()) {return;}
        if (e.getPacket() instanceof PlayerMoveC2SPacket p) {
            ((IPlayerMoveC2SPacket) p).setYaw(yaw);
            ((IPlayerMoveC2SPacket) p).setPitch(pitch);
        }
    }
}
