package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.util.MinecraftInterface;
import dev.l4j.currymod.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModuleComponent implements MinecraftInterface {

    private final Panel parent;
    private final Module module;

    private int x;
    private int y;
    private int width;
    private int height;
    private int yOffset;
    private boolean open;

    public ModuleComponent(Module module, Panel parent, int yOffset) {
        this.parent = parent;
        this.module = module;
        this.yOffset = yOffset;
        this.open = false;
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
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
    }
}
