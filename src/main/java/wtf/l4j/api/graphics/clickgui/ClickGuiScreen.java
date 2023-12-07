package wtf.l4j.api.graphics.clickgui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import wtf.l4j.CurryMod;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.utils.MinecraftInterface;
import wtf.l4j.impl.modules.client.ClickGui;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen implements MinecraftInterface {
    List<Window> windows;

    public ClickGuiScreen(){
        super(Text.literal("ClickGui"));
        windows = new ArrayList<>();
        int xOffset = 10;
        for(Category c : Category.values()){
            windows.add(new Window(c,xOffset,10));
            xOffset += Constants.WIDTH + 2;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if(ClickGui.noGradient.isEnabled()) {
            context.fillGradient(mc.getWindow().getScaledWidth(), mc.getWindow().getScaledHeight(), 0, 0, CurryMod.getInstance().getManagers().getColorManager().getRGBA(), Integer.MIN_VALUE);
        }
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
        CurryMod.getInstance().getManagers().getModuleManager().getModule(ClickGui.class).toggle();
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
