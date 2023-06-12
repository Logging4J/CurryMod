package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.PacketSendEvent;
import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import club.l4j.currymod.mixin.minecraft.IPlayerMoveC2SPacket;
import club.l4j.currymod.util.player.MovementUtils;
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

    public OptionSlider range = new OptionSlider("Range",1,6,1,6);
    public OptionBoolean rotate = new OptionBoolean("Rotations",true);

    public Aura(){
        addOptions(range, rotate);
    }

    float yaw, pitch;
    int delay;

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        if (mc.player.getAttackCooldownProgress(0) == 1) {
            delay++;
            if (delay >= 20) {
                List<PlayerEntity> closestPlayers = getClosestPlayers();
                if (!closestPlayers.isEmpty()) {
                    PlayerEntity target = closestPlayers.get(0);
                    attackTarget(target);
                }
            }
        }
    }

    @DemoListen
    public void onPacketSend(PacketSendEvent e) {
        if (nullCheck()) {return;}
        if (e.getPacket() instanceof PlayerMoveC2SPacket p && rotate.isEnabled()) {
            ((IPlayerMoveC2SPacket) p).setYaw(yaw);
            ((IPlayerMoveC2SPacket) p).setPitch(pitch);
        }
    }

    private List<PlayerEntity> getClosestPlayers() {
        return mc.world.getPlayers().stream()
                .filter(player -> player.distanceTo(mc.player) < range.getIntValue() && player != mc.player && player.isAlive())
                .sorted(Comparator.comparingDouble(player -> player.distanceTo(mc.player)))
                .collect(Collectors.toList());
    }

    private void attackTarget(PlayerEntity target) {
        float[] rotations = MovementUtils.getRotationsToEntity(target);
        yaw = rotations[0];
        pitch = rotations[1];

        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
