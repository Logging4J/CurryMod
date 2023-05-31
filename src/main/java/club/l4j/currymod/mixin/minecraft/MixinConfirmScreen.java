package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.event.Events;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfirmScreen.class)
public abstract class MixinConfirmScreen {

    @Shadow protected abstract void addButton(ButtonWidget button);

    @Inject(method = "addButtons", at = @At("HEAD"))
    protected void addButtons(int y, CallbackInfo ci) {
        addButton(ButtonWidget.builder(Text.of("Bypass"), button -> {
            Events.bypassResourcePack = true;
        }).build());
    }

}
