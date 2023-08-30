package wtf.l4j.api.graphics.clickgui.components;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.ModuleButton;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.utils.IGlobals;

public class Component implements IGlobals {

    public Option option;
    public ModuleButton moduleButton;
    public int yOffset;

    public Component(Option option, ModuleButton moduleButton, int yOffset){
        this.option = option;
        this.moduleButton = moduleButton;
        this.yOffset = yOffset;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {}

    public void mouseClicked(double mouseX, double mouseY, int button) {}

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public void keyPressed(int keyCode, int scanCode, int modifiers) {}


    public boolean hovered(int mouseX ,int mouseY ) {
        return mouseX > moduleButton.window.getX() && mouseX < moduleButton.window.getX() + Constants.WIDTH && mouseY > moduleButton.window.getY() + moduleButton.yOffset + yOffset && mouseY < moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT;
    }
}
