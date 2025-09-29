package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.Option;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.client.module.option.options.OptionKeybind;
import dev.l4j.currymod.client.module.option.options.OptionMode;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import dev.l4j.currymod.util.MinecraftInterface;
import dev.l4j.currymod.util.RenderUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleComponent implements MinecraftInterface {

    @Getter
    private final List<OptionComponent> optionComponents;
    private final Panel parent;

    @Getter
    private final Module module;

    @Setter
    private int yOffset;

    @Getter
    private boolean open;

    @Getter
    private int x;

    @Getter
    private int y;

    @Getter
    private int width;

    @Getter
    private int height;

    public ModuleComponent(Module module, Panel parent, int yOffset) {
        this.parent = parent;
        this.module = module;
        this.yOffset = yOffset;
        this.open = false;
        this.optionComponents = new ArrayList<>();

        int optionYOffset = parent.getHeight();
        for (Option<?> option : module.getOptions()) {
            if (option instanceof OptionBoolean optionBoolean) {
                optionComponents.add(new ToggleComponent(this, optionBoolean, optionYOffset));
                optionYOffset += parent.getHeight();
            } else if (option instanceof OptionNumber<?> optionNumber) {
                optionComponents.add(new SliderComponent(this, optionNumber, optionYOffset));
                optionYOffset += parent.getHeight();
            } else if (option instanceof OptionMode optionMode) {
                optionComponents.add(new ModeComponent(this, optionMode, optionYOffset));
                optionYOffset += parent.getHeight();
            }
        }
        optionComponents.add(new KeybindComponent(this, module.getKeybind(), optionYOffset));
    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.x = parent.getX();
        this.y = parent.getY() + yOffset;
        this.width = parent.getWidth();
        this.height = parent.getHeight();

        String openString = open ? "-" : "+";

        RenderUtils.drawRect(context, x, y, width, height, module.isEnabled() ? new Color(255, 255, 255, 150).getRGB() : new Color(255, 255, 255, 65).getRGB());
        context.drawText(mc.textRenderer, module.getName(),x + 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
        context.drawText(mc.textRenderer, openString, x + width - mc.textRenderer.getWidth(openString) - 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);

        if (open) {
            context.drawBorder( x, y, width, height, Color.WHITE.getRGB());
            optionComponents.forEach(optionComponent -> optionComponent.render(context, mouseX, mouseY, deltaTicks));
            context.drawBorder( x, y, width, height * (optionComponents.size() + 1), Color.WHITE.getRGB());
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY, x, y, width, height)) {
            if (button == 0) {
                module.toggle();
            } else if (button == 1) {
                open = !open;
                parent.updateButtons();
            }
        }

        if (open) optionComponents.forEach(optionComponent -> optionComponent.mouseClicked(mouseX, mouseY, button));
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (open) optionComponents.forEach(optionComponent -> optionComponent.mouseReleased(mouseX, mouseY, button));
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (open) optionComponents.forEach(optionComponent -> optionComponent.keyPressed(keyCode, scanCode, modifiers));
    }

    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX - width <= x && mouseY >= y && mouseY - height <= y;
    }
}
