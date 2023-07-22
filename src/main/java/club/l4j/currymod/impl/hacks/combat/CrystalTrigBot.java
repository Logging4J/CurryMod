package club.l4j.currymod.impl.hacks.combat;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

@Hack.Construct(name = "CrystalTrigBot", description = "Automatically attacks crystals if ur cursor is on it", category = Hack.Category.COMBAT)
public class CrystalTrigBot extends Hack {

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
