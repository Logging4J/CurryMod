package dev.logging4j.currymod.screen.clickgui;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.Option;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import dev.logging4j.currymod.module.option.options.OptionMode;
import dev.logging4j.currymod.module.option.options.OptionNumber;
import dev.logging4j.currymod.util.MinecraftInterface;
import dev.logging4j.currymod.util.RenderUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleComponent implements MinecraftInterface {
    private static final float ANIM_SPEED = 0.3f;

    @Getter private final List<OptionComponent> optionComponents;
    @Getter private int x, y;
    @Getter private int width, height;
    @Getter private final Panel parent;
    @Getter private float animatedHeight;
    private final Module module;
    @Setter private int yOffset;
    @Getter private boolean open;

    public ModuleComponent(Module module, Panel parent, int yOffset) {
        this.module = module;
        this.parent = parent;
        this.yOffset = yOffset;
        this.animatedHeight = parent.getHeight();

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
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        x = parent.getX();
        y = parent.getY() + yOffset;
        width = parent.getWidth();
        height = parent.getHeight();

        float target = getTargetHeight();
        animatedHeight += (target - animatedHeight) * ANIM_SPEED;

        if (Math.abs(animatedHeight - target) < 0.5f) {
            animatedHeight = target;
        }

        RenderUtils.drawRect(context, x + 3, y, width - 6, height, module.isEnabled() ? parent.getCategory().getColor().getRGB() : new Color(37, 37, 37).getRGB());
        context.drawTextWithShadow(mc.textRenderer, module.getName(), x + 5, y + (height - mc.textRenderer.fontHeight) / 2, -1);

        String openString = open ? "-" : "+";
        context.drawTextWithShadow(mc.textRenderer, openString, x + width - mc.textRenderer.getWidth(openString) - 5, y + (height - mc.textRenderer.fontHeight) / 2, -1);

        float visible = animatedHeight - height;
        if (visible <= 0) return;

        int scissorY = y + height;
        int scissorHeight = (int) visible;

        double scale = mc.getWindow().getScaleFactor();

        RenderSystem.enableScissor(
                (int) ((x + 3) * scale),
                (int) ((mc.getWindow().getScaledHeight() - (scissorY + scissorHeight)) * scale),
                (int) ((width - 6) * scale),
                (int) (scissorHeight * scale)
        );
        for (OptionComponent optionComponent : optionComponents) {
            optionComponent.render(context, mouseX, mouseY, delta);
        }
        RenderSystem.disableScissor();
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

    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public int getTargetHeight() {
        int base = parent.getHeight();

        if (!open) return base;

        return base + optionComponents.size() * parent.getHeight();
    }
}
