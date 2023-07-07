package club.l4j.currymod.util.render;

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

}
