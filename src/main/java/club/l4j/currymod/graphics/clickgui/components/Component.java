package club.l4j.currymod.graphics.clickgui.components;

import club.l4j.currymod.graphics.Common;
import club.l4j.currymod.graphics.clickgui.HackButton;
import club.l4j.currymod.feature.options.Option;
import club.l4j.currymod.util.IGlobals;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Component implements IGlobals {

    public Option option;
    public HackButton hackButton;
    public int yOffset;

    public Component(Option option, HackButton hackButton, int yOffset){
        this.option = option;
        this.hackButton = hackButton;
        this.yOffset = yOffset;
    }


    public void render(DrawContext context, int mouseX, int mouseY, float delta) {}

    public void mouseClicked(double mouseX, double mouseY, int button) {}

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public void keyPressed(int keyCode, int scanCode, int modifiers) {}


    public boolean hovered(int mouseX ,int mouseY ) {
        return mouseX > hackButton.window.x && mouseX < hackButton.window.x + Common.WIDTH && mouseY > hackButton.window.y+ hackButton.yOffset + yOffset && mouseY < hackButton.window.y + hackButton.yOffset + yOffset + Common.HEIGHT;
    }
}
