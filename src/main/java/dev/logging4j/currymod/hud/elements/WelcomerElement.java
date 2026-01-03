package dev.logging4j.currymod.hud.elements;

import dev.logging4j.currymod.hud.HudElement;
import dev.logging4j.currymod.module.option.options.OptionMode;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;


@HudElement.Info(name = "Welcomer", description = "says welcome whenever your ugly ahh face sees the monitor.", defaultX = 10, defaultY = 10)
public class WelcomerElement extends HudElement {

    @Override
    public void render(DrawContext context) {
        String welcome = "Billions must vibe, " + mc.player.getName().getLiteralString();
        context.drawTextWithShadow(mc.textRenderer, welcome, x, y, Color.WHITE.getRGB());

        this.width = mc.textRenderer.getWidth(welcome);
        this.height = mc.textRenderer.fontHeight;
    }
}
