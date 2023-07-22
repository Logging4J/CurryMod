package club.l4j.currymod.impl.gui.hudeditor;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.impl.gui.Constants;
import club.l4j.currymod.impl.gui.hudeditor.element.HudElement;
import club.l4j.currymod.core.util.IGlobals;
import net.minecraft.client.gui.DrawContext;

public class Button implements IGlobals {

    public Window window;
    public HudElement element;
    public int yOffset;
    boolean visible;

    public Button(Window window, HudElement element, int yOffset) {
        this.window = window;
        this.element = element;
        this.yOffset = yOffset;
        visible = false;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(window.x, window.y + yOffset, window.x + Constants.WIDTH, window.y + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer, element.getName(), window.x + 1, window.y + yOffset + 1, element.isEnabled() ? CurryMod.colorManager.getRGBA() : -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "-" : "+", window.x + Constants.WIDTH - 8, window.y + yOffset + 1, -1);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (hovered(window.x, window.y + yOffset, Constants.WIDTH, Constants.HEIGHT, (int) mouseX, (int) mouseY)) {
            if (button == 0) {
                element.toggle();
            } else {
                visible = !visible;
                window.updateButtons();
            }
        }
    }

    public static boolean hovered(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
