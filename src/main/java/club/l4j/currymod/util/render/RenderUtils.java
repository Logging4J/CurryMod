package club.l4j.currymod.util.render;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class RenderUtils {

    public static void drawImage(MatrixStack stack, int x, int y, int w, int h, Color color, String location) {
        Identifier i = new CurryIdentifier(location);
        if(color == null) {
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }else {
            RenderSystem.setShaderColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        }
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, i);
        DrawableHelper.drawTexture(stack, x, y, 0, 0, w, h, w, h);
        RenderSystem.disableBlend();
    }

}
