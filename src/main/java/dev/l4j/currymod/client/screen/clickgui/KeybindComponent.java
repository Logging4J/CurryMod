package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.module.option.options.OptionKeybind;
import dev.l4j.currymod.client.module.option.options.OptionMode;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.Locale;

public class KeybindComponent extends OptionComponent {

    private boolean binding;

    public KeybindComponent(ModuleComponent parent, OptionKeybind option, int yOffset) {
        super(parent, option, yOffset);
        this.binding = false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.x = this.parent.getX();
        this.y = this.parent.getY() + yOffset;
        this.width = this.parent.getWidth();
        this.height = this.parent.getHeight();

        RenderUtils.drawRect(context, x, y, width, height, new Color(255, 255, 255, 65).getRGB());
        context.drawBorder(x, y, width, height, new Color(255, 255, 255, 65).getRGB());

        String keyName = getKeyName(parent.getModule().getKeybind().getValue().getCode());
        context.drawText(mc.textRenderer, keyName, x + (width / 2) - (mc.textRenderer.getWidth(keyName) / 2), y + (height / 2) - (mc.textRenderer.fontHeight / 2), Color.WHITE.getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {

    }

    private String getKeyName(int key) {
        if (!binding) {
            if (key == 0 || key == GLFW.GLFW_KEY_UNKNOWN) {
                return "None";
            }
            String keyName = GLFW.glfwGetKeyName(key, 0);
            if (keyName == null) {
                return switch (key) {
                    case GLFW.GLFW_KEY_RIGHT_SHIFT -> "RShift";
                    case GLFW.GLFW_KEY_LEFT_SHIFT -> "LShift";
                    case GLFW.GLFW_KEY_LEFT_CONTROL -> "LControl";
                    case GLFW.GLFW_KEY_RIGHT_CONTROL -> "RControl";
                    case GLFW.GLFW_KEY_RIGHT_ALT -> "RAlt";
                    case GLFW.GLFW_KEY_LEFT_ALT -> "LAlt";
                    case GLFW.GLFW_KEY_PAGE_UP -> "PageUp";
                    case GLFW.GLFW_KEY_PAGE_DOWN -> "PageDown";
                    case GLFW.GLFW_KEY_UP -> "Up";
                    case GLFW.GLFW_KEY_DOWN -> "Down";
                    case GLFW.GLFW_KEY_LEFT -> "Left";
                    case GLFW.GLFW_KEY_RIGHT -> "Right";
                    default -> "None";
                };
            }
            return keyName.toUpperCase(Locale.ROOT);
        }
        return "...";
    }
}
