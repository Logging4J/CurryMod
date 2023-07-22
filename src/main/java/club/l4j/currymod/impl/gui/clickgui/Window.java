package club.l4j.currymod.impl.gui.clickgui;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.impl.gui.Constants;
import club.l4j.currymod.impl.gui.clickgui.components.Component;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.util.IGlobals;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class Window implements IGlobals {

    List<HackButton> modules;
    Hack.Category category;
    public int x,y,dragX,dragY;
    public boolean visible, dragging;

    public Window(Hack.Category category, int x, int y){
        this.category = category;
        this.x = x;
        this.y = y;
        modules = new ArrayList<>();

        visible = true;
        dragging = false;

        int offset = Constants.HEIGHT;

        for(Hack h : CurryMod.hackManager.getHackFeaturesInCategory(category)){
            modules.add(new HackButton(this,h,offset));
            offset += Constants.HEIGHT;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x,y,x + Constants.WIDTH,y + Constants.HEIGHT, CurryMod.colorManager.getRGBA());
        context.drawTextWithShadow(mc.textRenderer, category.getName(),x + 1, y + 1, -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "∨" : "∧",x  + Constants.WIDTH - 8, y + 1, -1);


        if(visible){
            for (HackButton moduleButton : modules) {
                moduleButton.render(context,mouseX,mouseY,delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible){
            for (HackButton moduleButton : modules) {
                moduleButton.mouseClicked(mouseX,mouseY,button);
            }
        }
        if(hovered(x,y,  Constants.WIDTH,  Constants.HEIGHT, (int) mouseX, (int) mouseY)) {
            if(button == 0){
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
            }else if(button == 1){
                visible = !visible;
            }
        }

    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button == 0 && dragging) dragging = false;

        for(HackButton mb : modules){
            mb.mouseReleased(mouseX,mouseY,button);
        }
    }
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        for (HackButton moduleButton : modules) {
            moduleButton.keyPressed(keyCode,scanCode,modifiers);
        }
    }


    public void updatePosition(int mouseX, int mouseY) {
        if(dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }
    }

    public void updateButtons() {
        int offset = Constants.HEIGHT;

        for(HackButton button : modules) {
            button.yOffset = offset;
            offset += Constants.HEIGHT;
            if(button.visible) {
                for(Component comp : button.options) {
                    offset+= Constants.HEIGHT;
                }
            }
        }
    }

    public static boolean hovered(int x ,int y ,int width, int height,int mouseX ,int mouseY ) {
        return mouseX > x  && mouseX< x  + width && mouseY > y && mouseY < y + height;
    }
}
