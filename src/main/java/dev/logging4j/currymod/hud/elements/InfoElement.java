package dev.logging4j.currymod.hud.elements;

import dev.logging4j.currymod.hud.HudElement;
import net.minecraft.client.gui.DrawContext;

// TODO
@HudElement.Info(name = "Info", description = "Shows FPS, PING etc.", defaultX = 20, defaultY = 20)
public class InfoElement extends HudElement {

    @Override
    public void render(DrawContext context) {
        context.drawTextWithShadow(mc.textRenderer, "FPS ", this.x, this.y, -1);

    }
}
