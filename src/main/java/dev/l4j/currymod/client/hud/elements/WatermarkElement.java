package dev.l4j.currymod.client.hud.elements;

import dev.l4j.currymod.GitInfo;
import dev.l4j.currymod.client.hud.HudElement;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

@HudElement.Info(name = "Watermark", description = "Client name with some version information", defaultX = 10, defaultY = 10)
public class WatermarkElement extends HudElement {

    @Override
    public void render(DrawContext context) {
        String watermark = "[ᛋᛋ] CurryMod v" + GitInfo.VERSION;
        context.drawTextWithShadow(mc.textRenderer, watermark, x, y, Color.WHITE.getRGB());

        this.width = mc.textRenderer.getWidth(watermark);
        this.height = mc.textRenderer.fontHeight;
    }
}
