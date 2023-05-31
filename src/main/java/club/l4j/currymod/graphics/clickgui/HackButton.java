package club.l4j.currymod.graphics.clickgui;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.Constants;
import club.l4j.currymod.graphics.clickgui.components.*;
import club.l4j.currymod.feature.options.Option;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class HackButton {

    TextRenderer tr = MinecraftClient.getInstance().textRenderer;
    List<Component> options;
    public Window window;
    public Hack hack;
    boolean visible;
    public int yOffset;

    public HackButton(Window window, Hack hack, int yOffset){
        this.window = window;
        this.hack = hack;
        this.yOffset = yOffset;

        visible = false;

        options = new ArrayList<>();

        int settingOffset = Constants.HEIGHT;
        for(Option s : hack.getOptions()){
            if(s.isBoolean(s)){
                options.add(new BooleanComponent(s,this,settingOffset));
            }
            else if(s.isNumber(s)){
                options.add(new NumberComponent(s,this,settingOffset));
            }
            else if(s.isMode(s)){
                options.add(new ModeComponent(s,this,settingOffset));
            }
            settingOffset += Constants.HEIGHT;
        }

        options.add(new BindComponent(null,this,settingOffset));

    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices,window.x,window.y + yOffset,window.x + Constants.WIDTH,window.y + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        tr.drawWithShadow(matrices,hack.getName(), window.x + 1, window.y + yOffset + 1,  hack.isEnabled() ? Constants.COLOR : -1);
        tr.drawWithShadow(matrices, visible ? "-" : "+", window.x +  Constants.WIDTH - 8, window.y + yOffset + 1, -1);
        if(visible) {
            for (Component comp : options) {
                comp.render(matrices, mouseX, mouseY, delta);
            }
        }

    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible) {
            for(Component comp : options){
                comp.mouseClicked(mouseX,mouseY,button);
            }
        }
        if(hovered(window.x,window.y  + yOffset, Constants.WIDTH, Constants.HEIGHT, (int) mouseX, (int) mouseY)){
            if(button == 0){
                hack.toggle();
            }else {
                visible =  !visible;
                window.updateButtons();
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for(Component comp : options){
            comp.mouseReleased(mouseX,mouseY,button);
        }
    }
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        for(Component comp : options){
            comp.keyPressed(keyCode, scanCode,modifiers);
        }
    }

    public static boolean hovered(int x ,int y ,int width, int height,int mouseX ,int mouseY ) {
        return mouseX > x  && mouseX< x  + width && mouseY > y && mouseY < y + height;
    }
}
