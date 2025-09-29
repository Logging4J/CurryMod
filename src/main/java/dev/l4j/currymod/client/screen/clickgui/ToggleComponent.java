package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.Option;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ToggleComponent extends OptionComponent {

    public ToggleComponent(ModuleComponent parent, OptionBoolean option, int yOffset) {
        super(parent, option, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.x = this.parent.getX();
        this.y = this.parent.getY() + yOffset;
        this.width = this.parent.getWidth();
        this.height = this.parent.getHeight();

        RenderUtils.drawRect(context, x, y, width, height, new Color(255, 255, 255, 65).getRGB());
        context.drawText(mc.textRenderer, option.getName() + ": ", x + 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
        context.drawText(mc.textRenderer, String.valueOf(option.getValue()), x + width - mc.textRenderer.getWidth(String.valueOf(option.getValue())) - 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {

    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {

    }
}
