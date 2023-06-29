package club.l4j.currymod.graphics.hudeditor;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.graphics.Constants;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.IGlobals;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class Window implements IGlobals {

    List<Button> elements;
    public int x,y,dragX,dragY;
    public boolean visible, dragging;

    public Window(int x, int y){
        this.x = x;
        this.y = y;
        elements = new ArrayList<>();

        visible = true;
        dragging = false;

        int offset = Constants.HEIGHT;

        for(HudElement h : CurryMod.hudManager.getHudElements()){
            elements.add(new Button(this,h,offset));
            offset += Constants.HEIGHT;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x,y,x + Constants.WIDTH,y + Constants.HEIGHT, CurryMod.uniColor.getRGBA());
        context.drawTextWithShadow(mc.textRenderer, "Hud Editor",x + 1, y + 1, -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "∨" : "∧",x  + Constants.WIDTH - 8, y + 1, -1);
        if(visible){
            for (Button moduleButton : elements) {
                moduleButton.render(context,mouseX,mouseY,delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible){
            for (Button moduleButton : elements) {
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
    }

    public void updatePosition(int mouseX, int mouseY) {
        if(dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }
    }

    public void updateButtons() {
        int offset = Constants.HEIGHT;

        for(Button button : elements) {
            button.yOffset = offset;
            offset += Constants.HEIGHT;
            if(button.visible) {

            }
        }
    }

    public static boolean hovered(int x ,int y ,int width, int height,int mouseX ,int mouseY ) {
        return mouseX > x  && mouseX< x  + width && mouseY > y && mouseY < y + height;
    }
}
