package dev.logging4j.currymod.util;

import dev.logging4j.currymod.CurryMod;
import lombok.experimental.UtilityClass;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;

import java.awt.*;

@UtilityClass
public class RenderUtils implements MinecraftInterface {

    public final ManagedShaderEffect GRAYSCALE_SHADER = ShaderEffectManager.getInstance()
            .manage(Identifier.of(CurryMod.MOD_ID, "shaders/post/grayscale.json"));

    public void drawRect(DrawContext context, int x, int y, int w, int h, int color) {
        context.fill(x, y, x + w, y + h, color);
    }

    public void drawNiggerPointingAtWords(DrawContext context, String description, int x, int y) {
        int textureWidth = 255;
        int textureHeight = 231;

        int textWidth = mc.textRenderer.getWidth(description);
        int textHeight = mc.textRenderer.fontHeight;

        int boxX = x + (textureWidth / 2) - (textWidth / 2) - 4;
        int boxY = y + 140;
        int boxWidth = textWidth + 8;
        int boxHeight = textHeight + 4;

        if (boxX + boxWidth > mc.getWindow().getScaledWidth()) {
            boxX -= boxX + boxWidth - mc.getWindow().getScaledWidth();
        }

        context.drawTexture(ResourceBank.DESCRIPTION_POINT, x, y, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);

        RenderUtils.drawRect(context, boxX, boxY, boxWidth, boxHeight, Color.BLACK.getRGB());

        context.drawBorder(boxX, boxY, boxWidth, boxHeight, Color.WHITE.getRGB());

        int textX = boxX + 4;
        int textY = boxY + 2;

        context.drawText(mc.textRenderer, description, textX, textY, Color.WHITE.getRGB(), false);
    }
}
