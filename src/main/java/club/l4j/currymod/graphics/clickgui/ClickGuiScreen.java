package club.l4j.currymod.graphics.clickgui;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.Constants;
import club.l4j.currymod.util.render.RenderUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
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
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        //RenderUtils.drawImage(matrices, 0,0,width, height, null, "textures/back.png");
        for (Window w : windows){
            w.render(matrices,mouseX,mouseY,delta);
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
        CurryMod.featureManager.getHack("ClickGUI").toggle();
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
