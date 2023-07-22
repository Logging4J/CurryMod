package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.util.TextUtil;
import club.l4j.currymod.core.util.render.RenderUtils;
import club.l4j.currymod.impl.hacks.client.Colors;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({TitleScreen.class})
public abstract class MixinTitleScreen extends Screen {

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo cl) {
        context.drawTextWithShadow(client.textRenderer,CurryMod.MOD_NAME + TextUtil.WHITE + "b" + CurryMod.VERSION + " By Logging4J :^)", 0, 1, Colors.rainbow.isEnabled() ? RenderUtils.rainbow(1, CurryMod.colorManager.getColor()) : CurryMod.colorManager.getRGBA());
    }
}