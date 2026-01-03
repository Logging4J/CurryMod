package dev.logging4j.currymod.screen.clickgui;

import dev.logging4j.currymod.module.option.options.OptionMode;
import dev.logging4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModeComponent extends OptionComponent {

    public ModeComponent(ModuleComponent parent, OptionMode option, int yOffset) {
        super(parent, option, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.x = this.parent.getX();
        this.y = this.parent.getY() + yOffset;
        this.width = this.parent.getWidth();
        this.height = this.parent.getHeight();

        RenderUtils.drawRect(context, x + 3, y, width - 6, height, new Color(37, 37, 37).getRGB());
        context.drawText(mc.textRenderer, option.getName() + ": ", x + 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
        context.drawText(mc.textRenderer, String.valueOf(option.getValue()), x + width - mc.textRenderer.getWidth(String.valueOf(option.getValue())) - 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY, x, y, width, height)) {
            if(button == 0){
                int i = 0;
                int enumIndex = 0;
                for (String enumName : ((OptionMode) option).getModes()) {
                    if (enumName.equals(option.getValue())) enumIndex = i;
                    i++;
                }
                if (enumIndex == ((OptionMode) option).getModes().size() - 1) {
                    ((OptionMode) option).setValue(((OptionMode) option).getModes().get(0));
                } else {
                    enumIndex++;
                    i = 0;
                    for (String enumName : ((OptionMode) option).getModes()) {
                        if (i == enumIndex) ((OptionMode) option).setValue(enumName);
                        i++;
                    }
                }
            } else if (button == 1) {
                int i = 0;
                int enumIndex = 0;
                for (String enumName : ((OptionMode) option).getModes()) {
                    if (enumName.equals(((OptionMode) option).getValue())) enumIndex = i;
                    i++;
                }
                if (enumIndex == 0) {
                    ((OptionMode) option).setValue(((OptionMode) option).getModes().get(((OptionMode) option).getModes().size() - 1));
                } else {
                    enumIndex--;
                    i = 0;
                    for (String enumName : ((OptionMode) option).getModes()) {
                        if (i == enumIndex) ((OptionMode) option).setValue(enumName);
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {

    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {

    }
}
