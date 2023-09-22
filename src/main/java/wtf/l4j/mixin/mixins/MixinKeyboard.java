package wtf.l4j.mixin.mixins;

import net.minecraft.client.Keyboard;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Module;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if(client.currentScreen == null) {
            if (action == GLFW.GLFW_PRESS) {
                for (Module module : CurryMod.getInstance().getManagers().getModuleManager().getModules()) {
                    if (key == module.getKey()) {
                        module.toggle();
                    }
                }
            }
        }
    }

}
