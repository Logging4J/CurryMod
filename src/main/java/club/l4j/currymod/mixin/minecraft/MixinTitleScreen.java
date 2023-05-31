package club.l4j.currymod.mixin.minecraft;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.util.TextUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({TitleScreen.class})
public abstract class MixinTitleScreen extends Screen {

    @Shadow
    private @Nullable String splashText = TextUtil.AQUA + "CurryMod.Club on Top";

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo cl) {
        String creds = TextUtil.AQUA + CurryMod.MOD_NAME + TextUtil.WHITE + "b" + CurryMod.VERSION + " By Logging4J :^)";
        client.textRenderer.drawWithShadow(matrices, creds, -1, 1, -1);
    }
}