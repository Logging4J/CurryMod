package dev.logging4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.movement.EntityControl;
import net.minecraft.entity.passive.StriderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StriderEntity.class)
public class StriderEntityMixin {

    @ModifyReturnValue(
            method = "isSaddled",
            at = @At("RETURN")
    )
    public boolean isSaddled(boolean original) {
        return CurryMod.getModuleManager().getModule(EntityControl.class).isEnabled() || original;
    }
}
