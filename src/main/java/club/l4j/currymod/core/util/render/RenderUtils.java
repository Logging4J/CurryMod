package club.l4j.currymod.core.util.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class RenderUtils {

    public static void drawImage(DrawContext context, int x, int y, int w, int h, Color color, String location) {
        CurryIdentifier i = new CurryIdentifier(location);
        if(color == null) {
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }else {
            RenderSystem.setShaderColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        }
        RenderSystem.enableBlend();
        context.drawTexture(i, x, y, 0, 0, w, h, w, h);
        RenderSystem.disableBlend();
    }

    public static int rainbow(float offset, Color color) {
        double timer = System.currentTimeMillis() % 1750.0 / 850.0;
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        float brightness = (float) (hsb[2] * Math.abs((offset + timer) % 1f - 0.55f) + 0.45f);
        return Color.HSBtoRGB(hsb[0], hsb[1], brightness);
    }

}
