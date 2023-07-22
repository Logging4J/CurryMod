package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.core.event.Events;
import club.l4j.currymod.impl.gui.spoofgui.SpoofScreen;
import club.l4j.currymod.core.util.TextUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MixinMultiPlayerScreen extends Screen {

    protected MixinMultiPlayerScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    protected void init(CallbackInfo ci) {
        ButtonWidget vanSpoof = ButtonWidget.builder(Text.of("ClientSpoofer"), (button) -> {
            client.setScreen(new SpoofScreen());
        }).position(1, height - (client.textRenderer.fontHeight * 4)).build();

        ButtonWidget rpb = ButtonWidget.builder(Text.of("RPB: " + (Events.bypassResourcePack ? TextUtil.GREEN+"True" : TextUtil.RED+"False")), (button) -> {
            Events.bypassResourcePack = !Events.bypassResourcePack;
            button.setMessage(Text.of("RPB: " + (Events.bypassResourcePack ? TextUtil.GREEN+"True" : TextUtil.RED+"False")));
        }).position(width - 151, height - (client.textRenderer.fontHeight * 4)).build();

        this.addDrawableChild(vanSpoof);
        this.addDrawableChild(rpb);

    }
}
