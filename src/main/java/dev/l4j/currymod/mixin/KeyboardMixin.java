package dev.l4j.currymod.mixin;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.listener.IKeyListener;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(
            method = "onKey",
            at = @At("HEAD")
    )
    public void onKeyHEAD(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        IKeyListener.KeyEvent event = new IKeyListener.KeyEvent(key, action);
        DietrichEvents2.global().call(IKeyListener.KeyEvent.ID, event);
    }
}
