package dev.logging4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.movement.EntityControl;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.StriderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PigEntity.class)
public class PigEntityMixin {

    @ModifyReturnValue(
            method = "isSaddled",
            at = @At("RETURN")
    )
    public boolean isSaddled(boolean original) {
        return CurryMod.getModuleManager().getModule(EntityControl.class).isEnabled() || original;
    }
}
