package dev.logging4j.currymod.hud.elements;

import dev.logging4j.currymod.hud.HudElement;
import dev.logging4j.currymod.util.WorldUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

//TODO Dynamic pos n dir based on screen pos
@HudElement.Info(name = "Location", description = "Displays Location information like direction and coordinates", defaultX = 20, defaultY = 20)
public class LocationElement extends HudElement {

    @Override
    public void render(DrawContext context) {
        Vec3d pos = mc.player.getPos();

        int netherX = (int) Math.round(pos.x / 8.0);
        int netherZ = (int) Math.round(pos.z / 8.0);

        float angle = (mc.player.getYaw() % 360 + 360) % 360;
        int index = (int)((angle + 45) / 90) % 4;
        String[] directions = {
                "South " + Formatting.GRAY + "[ " +Formatting.WHITE + "+Z" + Formatting.GRAY +" ]",
                "West " + Formatting.GRAY + "[ " +Formatting.WHITE + "-X" + Formatting.GRAY +" ]",
                "North " + Formatting.GRAY + "[ " +Formatting.WHITE + "-Z" + Formatting.GRAY +" ]",
                "East " + Formatting.GRAY + "[ " +Formatting.WHITE + "+X" + Formatting.GRAY +" ]"
        };

        boolean isNether = WorldUtils.getDimension() == WorldUtils.Dimension.Nether;

        String coordsStr = String.format(
                "%.2f, %.2f, %.2f " + Formatting.GRAY + "[" + Formatting.WHITE + "%d, %d" + Formatting.GRAY + "]",
                pos.x, pos.y, pos.z,
                isNether ? (int) (pos.x * 8) : netherX,
                isNether ? (int) (pos.z * 8) : netherZ
        );

        boolean flipHori = x < (mc.getWindow().getScaledWidth() / 2) - (mc.textRenderer.getWidth(coordsStr) / 2);

        context.drawText(mc.textRenderer, directions[index], flipHori ? x : x + mc.textRenderer.getWidth(coordsStr) - mc.textRenderer.getWidth(directions[index]), y, Color.WHITE.getRGB(), true);
        context.drawText(mc.textRenderer, coordsStr, x, y + mc.textRenderer.fontHeight, Color.WHITE.getRGB(), true);

        this.width = mc.textRenderer.getWidth(coordsStr);
        this.height = mc.textRenderer.fontHeight * 2;
    }
}
