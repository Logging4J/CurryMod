package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Hack.Construct(name = "Aura", description = "attacks ppl fo u", category = Hack.Category.COMBAT)
public class Aura extends Hack {

    public OptionSlider range = new OptionSlider("Range",1,6,1,4);
    public OptionBoolean rotate = new OptionBoolean("Rotations",true);
    public OptionBoolean players = new OptionBoolean("Players",true);
    public OptionBoolean animals = new OptionBoolean("Animals",true);
    public OptionBoolean mobs = new OptionBoolean("Monsters",true);

    public Aura(){
        addOptions(range, rotate,  players, animals, mobs);
    }

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        List<Entity> targets = getTargets().stream()
                .filter(entity -> entity.squaredDistanceTo(mc.player) < Math.pow(range.getIntValue(), 2) && entity != mc.player && entity.isAlive())
                .sorted(Comparator.comparingDouble(entity -> entity.distanceTo(mc.player))).toList();
        if(!targets.isEmpty()){
            if(mc.player.getAttackCooldownProgress(0) == 1) {
                attack(targets.get(0));
            }
        }
    }

    public void attack(Entity e){
        mc.interactionManager.attackEntity(mc.player, e);
        mc.player.swingHand(Hand.MAIN_HAND);
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
}
