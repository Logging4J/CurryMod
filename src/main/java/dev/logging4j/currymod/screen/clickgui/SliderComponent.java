package dev.logging4j.currymod.screen.clickgui;

import dev.logging4j.currymod.module.option.options.OptionNumber;
import dev.logging4j.currymod.util.MathUtils;
import dev.logging4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.math.RoundingMode;

public class SliderComponent extends OptionComponent {

    private boolean sliding;
    public SliderComponent(ModuleComponent parent, OptionNumber<?> option, int yOffset) {
        super(parent, option, yOffset);
        this.sliding = false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.x = this.parent.getX();
        this.y = this.parent.getY() + yOffset;
        this.width = this.parent.getWidth();
        this.height = this.parent.getHeight();

        double min = ((OptionNumber<?>) option).getMin().doubleValue();
        double max = ((OptionNumber<?>) option).getMax().doubleValue();
        double val = ((OptionNumber<?>) option).getValue().doubleValue();
        int filled = (int) ((val - min) / (max - min) * (width - 6));

        RenderUtils.drawRect(context, x + 3, y, width - 6, height, new Color(37, 37, 37).getRGB());

        RenderUtils.drawRect(context, x + 3, y + height - 2, filled, 2, parent.getParent().getCategory().getColor().getRGB());

        context.drawText(mc.textRenderer, option.getName() + ": ", x + 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
        context.drawText(mc.textRenderer, String.valueOf(option.getValue()), x + width - mc.textRenderer.getWidth(String.valueOf(option.getValue())) - 5, y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);

        if (sliding) {
            double mouseRelative = Math.min(width, Math.max(0, mouseX - x));
            double percent = mouseRelative / width;
            double newVal = min + percent * (max - min);

            Number increment = ((OptionNumber<?>) option).getIncrement();
            double rounded = MathUtils.roundToPlace(newVal, increment.doubleValue() < 1 ? 2 : 0, RoundingMode.HALF_DOWN);

            setSliderValue(rounded);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY, x, y, width, height) && button == 0) {
            sliding = true;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
    }

    @SuppressWarnings("unchecked")
    private void setSliderValue(double newValue) {
        if (option.getValue() instanceof Integer) {
            ((OptionNumber<Integer>) option).setValue((int) newValue);
        } else if (option.getValue() instanceof Float) {
            ((OptionNumber<Float>) option).setValue((float) newValue);
        } else if (option.getValue() instanceof Double) {
            ((OptionNumber<Double>) option).setValue(newValue);
        }
    }
}
