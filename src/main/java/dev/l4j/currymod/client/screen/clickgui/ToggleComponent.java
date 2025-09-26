package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.Option;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ToggleComponent extends OptionComponent{

    public ToggleComponent(ModuleComponent parent, Option<?> option, int yOffset) {
        super(parent, option, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.x = this.parent.getX();
        this.y = this.parent.getY() + this.parent.getHeight() + yOffset;
        this.width = this.parent.getWidth();
        this.height = this.parent.getHeight();

        RenderUtils.drawRect(context, x, y, width, height, new Color(255, 255, 255, 65).getRGB());
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {

    }
}
