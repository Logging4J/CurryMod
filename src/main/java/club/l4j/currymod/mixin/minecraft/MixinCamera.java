package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.impl.hacks.visual.FreeLook;
import club.l4j.currymod.impl.hacks.visual.NoRender;
import club.l4j.currymod.core.util.IGlobals;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class MixinCamera implements IGlobals {

    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Inject(method = "clipToSpace", at = @At("HEAD"), cancellable = true)
    private void onClipToSpace(double desiredCameraDistance, CallbackInfoReturnable<Double> info) {
        if (CurryMod.hackManager.getHack("CameraClip").isEnabled()) {
            info.setReturnValue(desiredCameraDistance);
        }
    }

    @Inject(method = "getSubmersionType", at = @At("HEAD"), cancellable = true)
    private void getSubmergedFluidState(CallbackInfoReturnable<CameraSubmersionType> ci) {
        if (CurryMod.hackManager.getHack("NoRender").isEnabled() && NoRender.liquid.isEnabled()) {
            ci.setReturnValue(CameraSubmersionType.NONE);
        }
    }

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V", ordinal = 0, shift = At.Shift.AFTER))
    public void update(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (CurryMod.hackManager.getHack("FreeLook").isEnabled() && focusedEntity instanceof ClientPlayerEntity p) {
            setRotation(FreeLook.cameraYaw, FreeLook.cameraPitch);
        }
    }

    @Inject(method = "update", at = @At(value = "TAIL"))
    public void outOfBody(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (CurryMod.hackManager.getHack("FreeLook").isEnabled() && focusedEntity instanceof ClientPlayerEntity) {
            mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        }
    }
}
