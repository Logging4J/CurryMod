package dev.logging4j.currymod.hud.elements;

import dev.logging4j.currymod.hud.HudElement;
import dev.logging4j.currymod.screen.hud.HudEditorScreen;
import dev.logging4j.currymod.util.WorldUtils;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@HudElement.Info(name = "TextRadar", description = "text radar thingy", defaultX = 10, defaultY = 10)
public class TextRadarElement extends HudElement {
    private static final DecimalFormat DISTANCE_FORMAT = new DecimalFormat("0.0");
    private final List<TextRadarPlayerEntry> fakeEntries = new ArrayList<>();

    @Override
    public void render(DrawContext context) {
        if (!(mc.currentScreen instanceof HudEditorScreen)) {
            fakeEntries.clear();
        }

        int yOffset = 0;
        int maxWidth = 0;

        List<TextRadarPlayerEntry> entries = new ArrayList<>();
        boolean hasRealPlayers = false;

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player.getUuid().equals(mc.player.getUuid())) continue;

            hasRealPlayers = true;

            TextRadarPlayerEntry entry = new TextRadarPlayerEntry(player, yOffset, false);
            entries.add(entry);

            maxWidth = Math.max(maxWidth, 8 + 3 + mc.textRenderer.getWidth(entry.getText()));

            yOffset += mc.textRenderer.fontHeight + 1;
        }

        if (!hasRealPlayers && mc.currentScreen instanceof HudEditorScreen) {
            if (fakeEntries.isEmpty()) {
                int fakeYOffset = 0;

                for (int i = 0; i < 5; i++) {
                    fakeEntries.add(
                            new TextRadarPlayerEntry(null, fakeYOffset, true)
                    );
                    fakeYOffset += mc.textRenderer.fontHeight + 1;
                }
            }

            for (TextRadarPlayerEntry entry : fakeEntries) {
                entries.add(entry);

                maxWidth = Math.max(
                        maxWidth,
                        8 + 3 + mc.textRenderer.getWidth(entry.getText())
                );
            }

            yOffset = fakeEntries.get(fakeEntries.size() - 1).yOffset
                    + mc.textRenderer.fontHeight + 1;
        }

        entries.forEach(textRadarPlayerEntry -> textRadarPlayerEntry.render(context));

        this.width = maxWidth;
        this.height = yOffset;
    }

    public class TextRadarPlayerEntry {

        private final PlayerEntity player;
        private final int yOffset;
        private final boolean fake;
        private final Identifier fakeSkin;

        @Getter
        private final String text;

        private TextRadarPlayerEntry(PlayerEntity player, int yOffset, boolean fake) {
            this.player = player;
            this.yOffset = yOffset;
            this.fake = fake;

            if (fake) {
                this.fakeSkin = DefaultSkinHelper.getTexture();
                this.text = buildFakeText();
            } else {
                this.fakeSkin = null;
                this.text = buildRealText();
            }
        }

        private String buildRealText() {
            int ping = WorldUtils.getPing(player);

            float absorption = player.getAbsorptionAmount();
            int health = Math.round(player.getHealth() + absorption);

            double healthPct = player.getHealth() / player.getMaxHealth();
            Formatting color = healthColor(healthPct);

            return player.getDisplayName().getString() + " " +
                    Formatting.GRAY + "[" +
                    Formatting.WHITE + DISTANCE_FORMAT.format(player.distanceTo(mc.player)) + "m" +
                    Formatting.GRAY + "] [" +
                    Formatting.WHITE + ping + "ms" +
                    Formatting.GRAY + "] [" +
                    color + health +
                    Formatting.GRAY + "]";
        }

        private String buildFakeText() {
            String name = "Player" + (mc.world.random.nextInt(900) + 100);
            double distance = 5 + mc.world.random.nextDouble() * 80;
            int ping = 10 + mc.world.random.nextInt(180);
            int health = 1 + mc.world.random.nextInt(20);

            Formatting color = healthColor(health / 20.0);

            return name + " " +
                    Formatting.GRAY + "[" +
                    Formatting.WHITE + DISTANCE_FORMAT.format(distance) + "m" +
                    Formatting.GRAY + "] [" +
                    Formatting.WHITE + ping + "ms" +
                    Formatting.GRAY + "] [" +
                    color + health +
                    Formatting.GRAY + "]";
        }

        private Formatting healthColor(double pct) {
            if (pct <= 0.333) return Formatting.RED;
            if (pct <= 0.666) return Formatting.YELLOW;
            return Formatting.GREEN;
        }

        public void render(DrawContext context) {
            if (fake) {
                PlayerSkinDrawer.draw(
                        context,
                        fakeSkin,
                        x,
                        y + yOffset,
                        8
                );
            } else {
                PlayerListEntry entry =
                        mc.getNetworkHandler().getPlayerListEntry(player.getUuid());

                if (entry != null) {
                    PlayerSkinDrawer.draw(
                            context,
                            entry.getSkinTextures(),
                            x,
                            y + yOffset,
                            8
                    );
                }
            }

            context.drawTextWithShadow(mc.textRenderer, text, x + 8 + 3, y + yOffset, 0xFFFFFFFF);
        }
    }
}
