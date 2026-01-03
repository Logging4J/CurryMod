package dev.logging4j.currymod.screen.hud;

import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.modules.client.HudEditor;
import dev.logging4j.currymod.screen.hud.components.HudComponent;
import dev.logging4j.currymod.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HudEditorScreen extends Screen {

    @Getter
    private static HudEditorScreen instance = new HudEditorScreen();

    private final List<HudComponent> hudComponents;

    public HudEditorScreen() {
        super(Text.literal("CurryMod HudEditor"));
        hudComponents = new ArrayList<>();

        CurryMod.getHudManager().getHudElements().forEach(hudElement -> {
            hudComponents.add(new HudComponent(hudElement));
        });
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        RenderUtils.drawRect(context, client.getWindow().getScaledWidth() / 2, 0, 1, client.getWindow().getScaledHeight(), Color.WHITE.getRGB());
        RenderUtils.drawRect(context, 0, client.getWindow().getScaledHeight() / 2, client.getWindow().getScaledWidth(), 1, Color.WHITE.getRGB());

        hudComponents.forEach(hudComponent -> hudComponent.render(context, mouseX, mouseY, deltaTicks));

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        hudComponents.forEach(hudComponent -> hudComponent.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        hudComponents.forEach(hudComponent -> hudComponent.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        hudComponents.forEach(hudComponent -> hudComponent.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount));
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public void close() {
        CurryMod.getModuleManager().getModule(HudEditor.class).setEnabled(false);
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void renderInGameBackground(DrawContext context) {
    }
}
