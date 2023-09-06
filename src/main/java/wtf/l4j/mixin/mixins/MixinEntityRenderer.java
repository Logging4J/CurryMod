package wtf.l4j.mixin.mixins;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.NoRender;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer<T extends Entity> {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void shouldRender(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if(Managers.getModuleManager().getModule(NoRender.class).get().isEnabled() && NoRender.arrows.isEnabled()){
            if(entity instanceof ArrowEntity) {
                cir.cancel();
            }
        }
    }

}
