package dev.logging4j.currymod.screen.clickgui;

import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.modules.client.ClickGUI;
import dev.logging4j.currymod.util.MinecraftInterface;
import dev.logging4j.currymod.util.RenderUtils;
import dev.logging4j.currymod.util.ResourceBank;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGUIScreen extends Screen implements MinecraftInterface {

    public static final int BG_PANEL = new Color(37, 37, 37).getRGB();
    public static final int BG_PANEL_DARK = new Color(26, 26, 26).getRGB();

    @Getter
    private static ClickGUIScreen instance = new ClickGUIScreen();

    private final List<Panel> panels;
    private float hoverRotation = 0f;

    public ClickGUIScreen() {
        super(Text.literal("CurryModClickGUI"));
        panels = new ArrayList<>();

        int xOffset = 10;
        for (Module.Category category : Module.Category.values()) {
            panels.add(new Panel(category, xOffset, 15, 140, 17));
            xOffset += 150;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        RenderUtils.GRAYSCALE_SHADER.setUniformValue("Intensity", 1.0f);
        RenderUtils.GRAYSCALE_SHADER.render(delta);

        int x = (width - 225) / 2;
        int y = (height - 225) / 2;
        int w = 212;
        int h = 212;
        float returnSpeed = 0.2f;

        boolean hovered = isHovered(mouseX, mouseY, x, y, w, h);

        if (hovered) {
            hoverRotation += delta * 6f;
        } else {
            hoverRotation += (0f - hoverRotation) * returnSpeed * delta;
        }

        context.getMatrices().push();
        context.getMatrices().translate(x + w / 2f, y + h / 2f, 0);

        context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(hoverRotation));

        context.getMatrices().translate(-w / 2f, -h / 2f, 0);
        context.drawTexture(ResourceBank.SHARTY, 0 , 0, 0, 0, w, h, w, h);
        context.getMatrices().pop();

        panels.forEach(panel -> panel.render(context, mouseX, mouseY, delta));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {}

    @Override
    public void close() {
        CurryMod.getModuleManager().getModule(ClickGUI.class).setEnabled(false);
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
