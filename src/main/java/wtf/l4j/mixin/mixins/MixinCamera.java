package wtf.l4j.mixin.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;

import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.impl.modules.visual.CameraClip;
import wtf.l4j.impl.modules.visual.FreeLook;

@Mixin(Camera.class)
public abstract class MixinCamera {

    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V", ordinal = 0, shift = At.Shift.AFTER))
    public void update(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (Managers.getModuleManager().getModule(FreeLook.class).orElseThrow().isEnabled() && focusedEntity instanceof ClientPlayerEntity p) {
            setRotation(FreeLook.cameraYaw, FreeLook.cameraPitch);
        }
    }

    @Inject(method = "update", at = @At(value = "TAIL"))
    public void outOfBody(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (Managers.getModuleManager().getModule(FreeLook.class).orElseThrow().isEnabled() && focusedEntity instanceof ClientPlayerEntity) {
            MinecraftClient mc = MinecraftClient.getInstance();
            mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        }
    }

    @Inject(method = "clipToSpace", at = @At("HEAD"), cancellable = true)
    private void onClipToSpace(double desiredCameraDistance, CallbackInfoReturnable<Double> info) {
        if (Managers.getModuleManager().getModule(CameraClip.class).get().isEnabled()) {
            info.setReturnValue(CameraClip.distance.getValue());
        }
    }

}
