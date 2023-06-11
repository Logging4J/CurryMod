package club.l4j.currymod.graphics.clickgui;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.Common;
import club.l4j.currymod.graphics.clickgui.components.*;
import club.l4j.currymod.feature.options.Option;
import club.l4j.currymod.util.IGlobals;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class HackButton implements IGlobals {

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

        int settingOffset = Common.HEIGHT;
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
            settingOffset += Common.HEIGHT;
        }

        options.add(new BindComponent(null,this,settingOffset));

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(window.x,window.y + yOffset,window.x + Common.WIDTH,window.y + yOffset + Common.HEIGHT, Common.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer, hack.getName(), window.x + 1, window.y + yOffset + 1,  hack.isEnabled() ? Common.COLOR : -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "-" : "+", window.x +  Common.WIDTH - 8, window.y + yOffset + 1, -1);
        if(visible) {
            for (Component comp : options) {
                comp.render(context, mouseX, mouseY, delta);
            }
        }
        if (hovered(window.x,window.y  + yOffset, Common.WIDTH, Common.HEIGHT, mouseX, mouseY)) {
            context.fill(0, mc.getWindow().getScaledHeight(), mc.textRenderer.getWidth(hack.getDesc()) + 1, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight - 1, Common.BACKGROUND_COLOR);
            context.drawTextWithShadow(mc.textRenderer, hack.getDesc(), 0, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight,  -1);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible) {
            for(Component comp : options){
                comp.mouseClicked(mouseX,mouseY,button);
            }
        }
        if(hovered(window.x,window.y  + yOffset, Common.WIDTH, Common.HEIGHT, (int) mouseX, (int) mouseY)){
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
