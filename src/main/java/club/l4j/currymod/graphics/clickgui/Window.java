package club.l4j.currymod.graphics.clickgui;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.graphics.Common;
import club.l4j.currymod.graphics.clickgui.components.Component;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.util.IGlobals;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

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

        int offset = Common.HEIGHT;

        for(Hack h : CurryMod.featureManager.getHackFeaturesInCategory(category)){
            modules.add(new HackButton(this,h,offset));
            offset += Common.HEIGHT;
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices,x,y,x + Common.WIDTH,y + Common.HEIGHT, Common.COLOR);
        mc.textRenderer.drawWithShadow(matrices,category.getName(),x + 1, y + 1, -1);
        mc.textRenderer.drawWithShadow(matrices,visible ? "∨" : "∧",x  + Common.WIDTH - 8, y + 1, -1);


        if(visible){
            for (HackButton moduleButton : modules) {
                moduleButton.render(matrices,mouseX,mouseY,delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible){
            for (HackButton moduleButton : modules) {
                moduleButton.mouseClicked(mouseX,mouseY,button);
            }
        }
        if(hovered(x,y,  Common.WIDTH,  Common.HEIGHT, (int) mouseX, (int) mouseY)) {
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
        int offset = Common.HEIGHT;

        for(HackButton button : modules) {
            button.yOffset = offset;
            offset += Common.HEIGHT;
            if(button.visible) {
                for(Component comp : button.options) {
                    offset+= Common.HEIGHT;
                }
            }
        }
    }

    public static boolean hovered(int x ,int y ,int width, int height,int mouseX ,int mouseY ) {
        return mouseX > x  && mouseX< x  + width && mouseY > y && mouseY < y + height;
    }
}
