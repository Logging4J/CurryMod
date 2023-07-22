package club.l4j.currymod.impl.gui.clickgui;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.impl.gui.Constants;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
    List<Window> windows;

    public ClickGuiScreen(){
        super(Text.literal("ClickGui"));
        windows = new ArrayList<>();
        int xOffset = 10;
        for(Hack.Category c : Hack.Category.values()){
            windows.add(new Window(c,xOffset,10));
            xOffset += Constants.WIDTH + 2;
        }

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for (Window w : windows){
            w.render(context,mouseX,mouseY,delta);
            w.updatePosition(mouseX,mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Window w : windows){
            w.mouseClicked(mouseX,mouseY,button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Window w : windows){
            w.mouseReleased(mouseX,mouseY,button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (Window w : windows){
            w.keyPressed(keyCode,scanCode,modifiers);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        CurryMod.hackManager.getHack("ClickGUI").toggle();
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
