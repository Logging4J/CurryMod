package dev.logging4j.currymod.screen.clickgui;

import dev.logging4j.currymod.module.option.options.OptionBoolean;
import dev.logging4j.currymod.util.RenderUtils;
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

        RenderUtils.drawRect(context, x + 3, y, width - 6, height, ((OptionBoolean) option).getValue() ? parent.getParent().getCategory().getColor().getRGB() : ClickGUIScreen.BG_PANEL);
        context.drawTextWithShadow(mc.textRenderer, option.getName(), x + 5, y + (height - mc.textRenderer.fontHeight) / 2, Color.WHITE.getRGB());
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY, x, y, width, height) && button == 0) {
            ((OptionBoolean) this.option).toggle();
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {}

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {

    }
}
