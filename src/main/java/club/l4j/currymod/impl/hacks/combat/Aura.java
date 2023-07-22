package club.l4j.currymod.impl.hacks.combat;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import club.l4j.currymod.core.util.player.PlayerUtil;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Hack.Construct(name = "Aura", description = "attacks ppl fo u", category = Hack.Category.COMBAT)
public class Aura extends Hack {

    public OptionSlider range = new OptionSlider("Range",1,6,1,4);
    public OptionBoolean players = new OptionBoolean("Players",true);
    public OptionBoolean animals = new OptionBoolean("Animals",true);
    public OptionBoolean mobs = new OptionBoolean("Monsters",true);
    public OptionBoolean rotate = new OptionBoolean("Rotations",true);

    public Aura(){
        addOptions(range,  players, animals, mobs, rotate);
    }

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()) return;
        List<Entity> targets = sortTargets(getTargets());
        if(!targets.isEmpty()){
            if(mc.player.getAttackCooldownProgress(0) == 1) {
                if(rotate.isEnabled()) {
                    float[] rotations = PlayerUtil.getRotationToEntity(targets.get(0));
                    sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(rotations[0], rotations[1], mc.player.isOnGround()));
                }
                attack(targets.get(0));
            }
        }
    }

    public void attack(Entity e){
        mc.player.swingHand(Hand.MAIN_HAND);
        mc.interactionManager.attackEntity(mc.player, e);
    }

    public List<Entity> getTargets(){
        List<Entity> targets = new ArrayList<>();
        for(Entity en : mc.world.getEntities()){
            if(mc.player.squaredDistanceTo(mc.player) < Math.pow(range.getIntValue(), 2)){
                if(en instanceof Monster && mobs.isEnabled()){
                    targets.add(en);
                } else if (en instanceof PlayerEntity p && players.isEnabled()){
                    if(p != mc.player) {
                        targets.add(en);
                    }
                } else if (en instanceof AnimalEntity && animals.isEnabled()){
                    targets.add(en);
                }
            }
        }
        return targets;
    }

    public List<Entity> sortTargets(List<Entity> targets){
        return targets.stream().filter(entity -> entity.squaredDistanceTo(mc.player) < Math.pow(range.getIntValue(), 2) && entity != mc.player && entity.isAlive()).sorted(Comparator.comparingDouble(entity -> entity.distanceTo(mc.player))).toList();
    }
}
