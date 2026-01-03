package dev.logging4j.currymod.screen.clickgui;

import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.util.MinecraftInterface;
import dev.logging4j.currymod.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel implements MinecraftInterface {

    private static final float ANIM_SPEED = 0.3f;

    private final List<ModuleComponent> moduleComponents;
    @Getter private final Module.Category category;
    @Getter private final int width, height;

    @Getter private int x, y;
    private boolean open;
    private float animatedHeight;

    private boolean dragging;
    private int dragX, dragY;

    public Panel(Module.Category category, int x, int y, int width, int height) {
        this.height = height;
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.animatedHeight = height;

        this.open = true;
        this.dragging = false;
        this.moduleComponents = new ArrayList<>();

        int yOffset = height;
        for (Module module : CurryMod.getModuleManager().getModulesInCategory(category)) {
            moduleComponents.add(new ModuleComponent(module, this, yOffset));
            yOffset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        float target = getPanelHeight();
        animatedHeight += (target - animatedHeight) * ANIM_SPEED;

        if (Math.abs(animatedHeight - target) < 0.5f) {
            animatedHeight = target;
        }

        RenderUtils.drawRect(context, x, y, width, (int) animatedHeight, category.getColor().getRGB());
        RenderUtils.drawRect(context, x + 1, y + 1, width - 2, (int) animatedHeight - 2, new Color(26, 26, 26).getRGB());
        context.drawTextWithShadow(mc.textRenderer, category.getName(), x + 5, y + (height - mc.textRenderer.fontHeight) / 2, -1);

        String openString = open ? "∨" : "∧" ;

        context.drawTextWithShadow(mc.textRenderer, openString, x + width - mc.textRenderer.getWidth(openString) - 5, y + (height - mc.textRenderer.fontHeight) / 2, -1);
        if (open) moduleComponents.forEach(moduleComponent -> moduleComponent.render(context, mouseX, mouseY, delta));
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

    private int getPanelHeight() {
        int total = height;
        if (!open) return total;
        for (ModuleComponent moduleComponent : moduleComponents) {
            total += height;
            if (moduleComponent.isOpen()) {
                total += moduleComponent.getOptionComponents().size() * height;
            }
        }

        return total + 3;
    }

    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX - width <= x && mouseY >= y && mouseY - height <= y;
    }
}
