package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.Option;
import dev.l4j.currymod.util.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;

public abstract class OptionComponent implements MinecraftInterface {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int yOffset;

    protected ModuleComponent parent;
    protected Option<?> option;

    public OptionComponent(ModuleComponent parent, Option<?> option, int yOffset) {
        this.parent = parent;
        this.option = option;
        this.yOffset = yOffset;
    }

    public abstract void render(DrawContext context, int mouseX, int mouseY, float deltaTicks);
    public abstract void mouseClicked(double mouseX, double mouseY, int button);
    public abstract void mouseReleased(double mouseX, double mouseY, int button) ;
}
