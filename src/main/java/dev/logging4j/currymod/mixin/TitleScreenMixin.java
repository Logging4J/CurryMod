package dev.logging4j.currymod.mixin;

import dev.logging4j.currymod.GitInfo;
import dev.logging4j.currymod.screen.title.HomieText;
import dev.logging4j.currymod.util.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.Window;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen implements MinecraftInterface {


    @Shadow @Nullable private SplashTextRenderer splashText = new SplashTextRenderer(Formatting.LIGHT_PURPLE + "Free My Nigga P.Diddy!");
    @Unique private List<HomieText> homieTexts;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    public void renderTAIL(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.drawText(mc.textRenderer, String.format("[%s%s%s] %sv%s+%s", Formatting.GREEN, GitInfo.MAVEN_NAME, Formatting.WHITE, Formatting.GRAY, GitInfo.VERSION, GitInfo.GIT_SHA.substring(0, 7)), 1, 1, -1, true);
        context.drawText(mc.textRenderer, String.format("[%sBuild Date%s] %s%s", Formatting.GREEN, Formatting.WHITE, Formatting.GRAY, GitInfo.BUILD_DATE), 1, 1 + mc.textRenderer.fontHeight, -1, true);
        context.drawText(mc.textRenderer, String.format("[%sBranch%s] %s%s", Formatting.GREEN, Formatting.WHITE, Formatting.GRAY, GitInfo.GIT_BRANCH), 1, 1 + (mc.textRenderer.fontHeight * 2), -1, true);

        homieTexts.forEach(homieText -> {
            homieText.updatePos();
            context.drawTextWithShadow(mc.textRenderer, homieText.getName(), (int) homieText.getX(), (int) homieText.getY(), homieText.getColor().getRGB());
        });
    }

    @Inject(
            method = "init",
            at = @At("TAIL")
    )
    public void init(CallbackInfo ci) {
        homieTexts = new ArrayList<>();
        Window window = client.getWindow();
        String[] homies = {
                "Logging4J", "_WMS", "Audsis", "Tuco", "TudouCat", "Slimmask", "Fuzse",
                "NewHag", "SpikeStinger", "DevOfDeath", "Baitpl",
                "dogbat", "3958", "Anthhurs", "BossStack", "Troppy"
        };
        Arrays.stream(homies).forEach(homie -> {
            homieTexts.add(new HomieText(homie, window.getScaledWidth(), window.getScaledHeight()));
        });
    }


    @Redirect(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 4
            )
    )
    public Element initINVOKE$addDrawableChild(TitleScreen instance, Element element){
        return null;
    }
}
