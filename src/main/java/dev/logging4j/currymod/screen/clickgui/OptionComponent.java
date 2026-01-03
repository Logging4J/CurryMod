package dev.logging4j.currymod.screen.clickgui;

import dev.logging4j.currymod.module.option.Option;
import dev.logging4j.currymod.util.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;

public abstract class OptionComponent implements MinecraftInterface {

    protected int x, y, width, height, yOffset;
    protected ModuleComponent parent;
    protected Option<?> option;

    public OptionComponent(ModuleComponent parent, Option<?> option, int yOffset) {
        this.parent = parent;
        this.option = option;
        this.yOffset = yOffset;
    }

    public abstract void render(DrawContext context, int mouseX, int mouseY, float deltaTicks);

    public abstract void mouseClicked(double mouseX, double mouseY, int button);

    public abstract void mouseReleased(double mouseX, double mouseY, int button);

    public abstract void keyPressed(int keyCode, int scanCode, int modifiers);

    protected boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX - width <= x && mouseY >= y && mouseY - height <= y;
    }
}
