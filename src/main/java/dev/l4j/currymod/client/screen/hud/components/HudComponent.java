package dev.l4j.currymod.client.screen.hud.components;

import dev.l4j.currymod.client.hud.HudElement;
import dev.l4j.currymod.util.MinecraftInterface;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import org.joml.Matrix3x2fStack;

import java.awt.*;

public class HudComponent implements MinecraftInterface {

    private final HudElement hudElement;

    private boolean dragging;
    private int dragX, dragY;

    public HudComponent(HudElement hudElement) {
        this.hudElement = hudElement;
        this.dragging = false;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        Matrix3x2fStack stack = context.getMatrices();

        if (dragging) {
            hudElement.x = (int) ((mouseX - dragX) / hudElement.scale);
            hudElement.y = (int) ((mouseY - dragY) / hudElement.scale);
        }

        clampHudElement();

        stack.pushMatrix();

        stack.scale(hudElement.scale);

        hudElement.render(context);

        stack.popMatrix();

        int scaledX = (int) (hudElement.x * hudElement.scale);
        int scaledY = (int) (hudElement.y * hudElement.scale);
        int scaledWidth = (int) (hudElement.width * hudElement.scale);
        int scaledHeight = (int) (hudElement.height * hudElement.scale);

        context.drawBorder(scaledX, scaledY, scaledWidth, scaledHeight, Color.WHITE.getRGB());

        if (!hudElement.isShown()) {
            context.fill(scaledX, scaledY, scaledX + scaledWidth, scaledY + scaledHeight, new Color(255, 0, 0, 100).getRGB());
        }

        if (isHovered(mouseX, mouseY, scaledX, scaledY, scaledWidth, scaledHeight)) {
            String scaleString = String.format("%s Element Scale: %f", hudElement.getName(), hudElement.scale);
            context.drawTextWithShadow(mc.textRenderer, scaleString,
                    (mc.getWindow().getScaledWidth() / 2) - (mc.textRenderer.getWidth(scaleString) / 2),
                    (mc.getWindow().getScaledHeight() / 2) - (mc.textRenderer.fontHeight / 2),
                    Color.WHITE.getRGB()
            );

            RenderUtils.drawNegroPointingAtDescription(context, hudElement.getDescription(), mc.getWindow().getScaledWidth() - 260, mc.getWindow().getScaledHeight() - 230);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        int scaledX = (int) (hudElement.x * hudElement.scale);
        int scaledY = (int) (hudElement.y * hudElement.scale);
        int scaledWidth = (int) (hudElement.width * hudElement.scale);
        int scaledHeight = (int) (hudElement.height * hudElement.scale);

        if (isHovered(mouseX, mouseY, scaledX, scaledY, scaledWidth, scaledHeight)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) ((mouseX - scaledX));
                dragY = (int) ((mouseY - scaledY));
            } else if (button == 1) {
                hudElement.setShown(!hudElement.isShown());
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button == 0 && dragging) dragging = false;
    }

    public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int scaledX = (int) (hudElement.x * hudElement.scale);
        int scaledY = (int) (hudElement.y * hudElement.scale);
        int scaledWidth = (int) (hudElement.width * hudElement.scale);
        int scaledHeight = (int) (hudElement.height * hudElement.scale);

        if (isHovered(mouseX, mouseY, scaledX, scaledY, scaledWidth, scaledHeight)) {
            if (hudElement.scale == 0.1f && verticalAmount < 0) return;

            hudElement.scale += (float) (verticalAmount / 10);
        }
    }
    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX - width <= x && mouseY >= y && mouseY - height <= y;
    }

    public void clampHudElement() {
        int scaledWidth = (int) (hudElement.width * hudElement.scale);
        int scaledHeight = (int) (hudElement.height * hudElement.scale);

        int maxX = mc.getWindow().getScaledWidth() - scaledWidth;
        int maxY = mc.getWindow().getScaledHeight() - scaledHeight;

        hudElement.x = Math.max(0, Math.min(hudElement.x, (int)(maxX / hudElement.scale)));
        hudElement.y = Math.max(0, Math.min(hudElement.y, (int)(maxY / hudElement.scale)));
    }
}
