package dev.logging4j.currymod.hud.elements;

import dev.logging4j.currymod.hud.HudElement;
import dev.logging4j.currymod.util.WorldUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;

import java.text.DecimalFormat;

@HudElement.Info(name = "TextRadar", description = "text radar thingy", defaultX = 10, defaultY = 10)
public class TextRadarElement extends HudElement {

    @Override
    public void render(DrawContext context) {

        int yOffset = 0;
        int maxWidth = 0;
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player.getDisplayName().getString().equals(mc.player.getDisplayName().getString())) continue;

            PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(player.getUuid());
            int ping = WorldUtils.getPing(player);

            float absorption = player.getAbsorptionAmount();
            int health = Math.round(player.getHealth() + absorption);
            double healthPercentage = health / (player.getMaxHealth() + absorption);

            Formatting healthColor;
            if (healthPercentage <= 0.333) {
                healthColor = Formatting.RED;
            } else if (healthPercentage <= 0.666) {
                healthColor = Formatting.YELLOW;
            } else {
                healthColor = Formatting.GREEN;
            }

            String text = player.getDisplayName().getString() + " " + Formatting.GRAY + "[" + Formatting.WHITE + new DecimalFormat("0.0").format(player.distanceTo(mc.player)) + "m" + Formatting.GRAY + "] [" + Formatting.WHITE + ping + "ms" + Formatting.GRAY + "] [" + healthColor + health + Formatting.GRAY + "]";

            PlayerSkinDrawer.draw(context, entry.getSkinTextures(), x, y + yOffset, 8);

            context.drawTextWithShadow(mc.textRenderer, text, x + 8 + 3, y + yOffset, -1);

            int textWidth = 8 + 3 + mc.textRenderer.getWidth(text);
            if (maxWidth < textWidth) {
                maxWidth =  textWidth;
            }
            yOffset += mc.textRenderer.fontHeight + 1;
        }

        this.width = maxWidth;
        this.height = yOffset;
    }
}
