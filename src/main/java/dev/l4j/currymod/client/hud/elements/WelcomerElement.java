package dev.l4j.currymod.client.hud.elements;

import dev.l4j.currymod.client.hud.HudElement;
import net.minecraft.client.gui.DrawContext;
import dev.l4j.currymod.client.module.option.options.OptionMode;

import java.awt.*;


@HudElement.Info(name = "Welcomer", description = "says welcome whenever your ugly ahh face sees the monitor.", defaultX = 10, defaultY = 10)
public class WelcomerElement extends HudElement {

    private final OptionMode optionMode = new OptionMode("Mode", "Time", "Time");

    @Override
    public void render(DrawContext context) {
        String welcome = "Billions must vibe, " + mc.player.getName().getLiteralString();
        context.drawTextWithShadow(mc.textRenderer, welcome, x, y, Color.WHITE.getRGB());

        if (optionMode.getValue().equals("Time")) {}

        this.width = mc.textRenderer.getWidth(welcome);
        this.height = mc.textRenderer.fontHeight;
    }
}
