package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.util.MinecraftInterface;
import dev.l4j.currymod.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Panel implements MinecraftInterface {

    private final Module.Category category;
    private final List<ModuleComponent> moduleComponents;

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean open;

    private boolean dragging;
    private int dragX, dragY;

    public Panel(Module.Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.open = true;
        this.dragging = false;
        this.moduleComponents = new ArrayList<>();

        int yOffset = height;
        for (Module module : CurryMod.INSTANCE.moduleManager.getModulesInCategory(category)) {
            moduleComponents.add(new ModuleComponent(module, this, yOffset));
            yOffset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        String openString = open ? "∧" : "∨";

        RenderUtils.drawRect(context, x, y, width, height, new Color(255, 255, 255, 100).getRGB());
        context.drawText(mc.textRenderer, category.getName(), x + 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
        context.drawText(mc.textRenderer, openString, x + width - mc.textRenderer.getWidth(openString) - 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);

        if (open) moduleComponents.forEach(moduleComponent -> moduleComponent.render(context, mouseX, mouseY, deltaTicks));
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY, x, y, width, height)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) ((mouseX - x));
                dragY = (int) ((mouseY - y));
            } else if (button == 1) {
                open = !open;
            }
        }
        if (open) moduleComponents.forEach(moduleComponent -> moduleComponent.mouseClicked(mouseX, mouseY, button));
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button == 0 && dragging) dragging = false;
        if (open) moduleComponents.forEach(moduleComponent -> moduleComponent.mouseReleased(mouseX, mouseY, button));
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (open) moduleComponents.forEach(moduleComponent -> moduleComponent.keyPressed(keyCode, scanCode, modifiers));
    }

    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX - width <= x && mouseY >= y && mouseY - height <= y;
    }

    public void updateButtons() {
        int offset = height;
        for(ModuleComponent moduleComponent : moduleComponents) {
            moduleComponent.setYOffset(offset);
            offset += height;
            if(moduleComponent.isOpen()) {
                for(OptionComponent comp : moduleComponent.getOptionComponents()) {
                    offset += height;
                }
            }
        }
    }
}
