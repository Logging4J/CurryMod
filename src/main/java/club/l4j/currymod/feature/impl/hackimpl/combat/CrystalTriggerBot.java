package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

@Hack.Construct(name = "CrystalTriggerBot", description = "Automatically attacks crystals if ur cursor is on it", category = Hack.Category.COMBAT)
public class CrystalTriggerBot extends Hack {

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()) return;
        if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult) mc.crosshairTarget).getEntity();
            if (!target.isAlive()) return;
            if (target instanceof EndCrystalEntity) {
                if (mc.player.canSee(target) && mc.player.distanceTo(target) < 5) {
                    mc.interactionManager.attackEntity(mc.player, target);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }
}
