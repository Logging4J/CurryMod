package wtf.l4j.api.graphics.hudeditor;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.MinecraftInterface;

public class Button implements MinecraftInterface {

    public Panel panel;
    public HudElement element;
    public int yOffset;
    boolean visible;

    public Button(Panel panel, HudElement element, int yOffset) {
        this.panel = panel;
        this.element = element;
        this.yOffset = yOffset;
        visible = false;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(panel.x, panel.y + yOffset, panel.x + Constants.WIDTH, panel.y + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer, element.getName(), panel.x + 1, panel.y + yOffset + 1, element.isEnabled() ? CurryMod.getInstance().getManagers().getColorManager().getRGBA() : -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "-" : "+", panel.x + Constants.WIDTH - 8, panel.y + yOffset + 1, -1);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (hovered(panel.x, panel.y + yOffset, Constants.WIDTH, Constants.HEIGHT, (int) mouseX, (int) mouseY)) {
            if (button == 0) {
                element.toggle();
            } else {
                visible = !visible;
                panel.updateButtons();
            }
        }
    }

    public static boolean hovered(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }


}
