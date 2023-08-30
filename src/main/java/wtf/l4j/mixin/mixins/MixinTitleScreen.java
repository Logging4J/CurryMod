package wtf.l4j.mixin.mixins;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo cl) {
        context.drawTextWithShadow(textRenderer, "CurryMod.Club " + TextUtil.WHITE + "b" + CurryMod.VERSION + " By Logging4J :^)", 0, 1, Managers.getColorManager().getRGBA());
    }
}
