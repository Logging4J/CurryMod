package wtf.l4j.api.graphics.hudeditor;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.MinecraftInterface;

import java.util.ArrayList;
import java.util.List;

public class Panel implements MinecraftInterface {

    List<Button> elements;
    public int x,y,dragX,dragY;
    public boolean visible, dragging;

    public Panel(int x, int y){
        this.x = x;
        this.y = y;
        elements = new ArrayList<>();

        visible = true;
        dragging = false;

        int offset = Constants.HEIGHT;

        for(HudElement h : CurryMod.getInstance().getManagers().getHudManager().getHudElements()){
            elements.add(new Button(this,h,offset));
            offset += Constants.HEIGHT;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x,y,x + Constants.WIDTH,y + Constants.HEIGHT, CurryMod.getInstance().getManagers().getColorManager().getRGBA());
        context.drawTextWithShadow(mc.textRenderer, "Hud Editor",x + 1, y + 1, -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "∨" : "∧",x  + Constants.WIDTH - 8, y + 1, -1);
        if(visible){
            for (Button hudbutton : elements) {
                hudbutton.render(context,mouseX,mouseY,delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible){
            for (Button hudbutton : elements) {
                hudbutton.mouseClicked(mouseX,mouseY,button);
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
