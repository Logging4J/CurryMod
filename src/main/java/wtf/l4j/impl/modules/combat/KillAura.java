package wtf.l4j.impl.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.newevent.GameTickListener;
import wtf.l4j.api.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ModuleInfo(name = "KillAura", desc = "Attacks people in range", category = Category.COMBAT)
public class KillAura extends Module implements GameTickListener {

    public OptionSlider range = new OptionSlider("Range",1,6,1,4);
    public OptionBoolean players = new OptionBoolean("Players",true);
    public OptionBoolean animals = new OptionBoolean("Animals",true);
    public OptionBoolean mobs = new OptionBoolean("Monsters",true);
    public OptionBoolean rotate = new OptionBoolean("Rotations",true);

    private List<Entity> targets;

    public KillAura(){
        addOptions(range,  players, animals, mobs, rotate);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        targets = sortTargets(getTargets());
        if(!targets.isEmpty()){
            if(mc.player.getAttackCooldownProgress(0) == 1) {
                if(rotate.isEnabled()) {
                    float[] rotations = PlayerUtils.getRotationToEntity(targets.get(0));
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
        return targets.stream().filter(entity -> entity.squaredDistanceTo(mc.player) < Math.pow(range.getIntValue(), 2) && entity.isAlive()).sorted(Comparator.comparingDouble(entity -> entity.distanceTo(mc.player))).toList();
    }

}
