package dev.l4j.currymod.util;

import com.mojang.blaze3d.systems.ProjectionType;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.l4j.currymod.mixin.accessor.ProjectionMatrix2Accessor;
import lombok.experimental.UtilityClass;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.ProjectionMatrix2;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;

import java.awt.*;

@UtilityClass
public class RenderUtils implements MinecraftInterface{

    private static final ProjectionMatrix2 matrix = new ProjectionMatrix2("currymod-projection-matrix", -10, 100, true);
    public static final Matrix4f projection = new Matrix4f();
    public static boolean rendering3D = true;
    public static double frameTime;


    public void unscaledProjection() {
        float width = mc.getWindow().getFramebufferWidth();
        float height = mc.getWindow().getFramebufferHeight();

        RenderSystem.setProjectionMatrix(matrix.set(width, height), ProjectionType.ORTHOGRAPHIC);
        RenderUtils.projection.set(((ProjectionMatrix2Accessor) matrix).invoke$getMatrix(width, height));

        rendering3D = false;
    }

    public void scaledProjection() {
        float width = (float) (mc.getWindow().getFramebufferWidth() / mc.getWindow().getScaleFactor());
        float height = (float) (mc.getWindow().getFramebufferHeight() / mc.getWindow().getScaleFactor());

        RenderSystem.setProjectionMatrix(matrix.set(width, height), ProjectionType.PERSPECTIVE);
        RenderUtils.projection.set(((ProjectionMatrix2Accessor) matrix).invoke$getMatrix(width, height));

        rendering3D = true;
    }

    public void drawRect(DrawContext context, int x, int y, int w, int h, int color) {
        context.fill(x, y, x + w, y + h, color);
    }

    public static void drawNegroPointingAtDescription(DrawContext context, String description, int x, int y) {
        int textureWidth = 255;
        int textureHeight = 231;

        int textWidth = mc.textRenderer.getWidth(description);
        int textHeight = mc.textRenderer.fontHeight;

        int boxX = x + (textureWidth / 2) - (textWidth / 2) - 4;
        int boxY = y + 140;
        int boxWidth = textWidth + 8;
        int boxHeight = textHeight + 4;

        if (boxX + boxWidth > mc.getWindow().getScaledWidth() - 10) {
            boxX -= boxX + boxWidth - mc.getWindow().getScaledWidth() - 10;
        }

        context.drawTexture(RenderPipelines.GUI_TEXTURED, ResourceBank.DESCRIPTION_POINT, x, y, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);

        RenderUtils.drawRect(context, boxX, boxY, boxWidth, boxHeight, Color.BLACK.getRGB());

        context.drawBorder(boxX, boxY, boxWidth, boxHeight, Color.WHITE.getRGB());

        int textX = boxX + 4;
        int textY = boxY + 2;

        context.drawText(mc.textRenderer, description, textX, textY, Color.WHITE.getRGB(), false);
    }
}
