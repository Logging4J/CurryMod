package dev.l4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.modules.movement.EntityControl;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @ModifyReturnValue(
            method = "hasSaddleEquipped",
            at = @At("RETURN")
    )
    private boolean hasSaddleEquippedRETURN(boolean original) {
        return CurryMod.INSTANCE.moduleManager.getModule(EntityControl.class).isEnabled() || original;
    }
}
