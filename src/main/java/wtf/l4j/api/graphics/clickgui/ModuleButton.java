package wtf.l4j.api.graphics.clickgui;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.components.*;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.IGlobals;

import java.util.ArrayList;
import java.util.List;


public class ModuleButton implements IGlobals {

    List<Component> options;
    public Window window;
    public Module module;
    boolean visible;
    public int yOffset;

    public ModuleButton(Window window, Module module, int yOffset){
        this.window = window;
        this.module = module;
        this.yOffset = yOffset;

        visible = false;

        options = new ArrayList<>();

        int settingOffset = Constants.HEIGHT;
        for(Option s : module.getOptions()){
            if(s instanceof OptionBoolean){
                options.add(new BooleanComponent(s,this,settingOffset));
            }
            else if(s instanceof OptionSlider){
                options.add(new NumberComponent(s,this,settingOffset));
            }
            else if(s instanceof OptionMode){
                options.add(new ModeComponent(s,this,settingOffset));
            }
            settingOffset += Constants.HEIGHT;
        }

        options.add(new BindComponent(null,this,settingOffset));

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(window.getX(),window.getY() + yOffset,window.getX() + Constants.WIDTH,window.getY() + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer, module.getName(), window.getX() + 1, window.getY() + yOffset + 1,  module.isEnabled() ? Managers.getColorManager().getRGBA() : -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "-" : "+", window.getX() +  Constants.WIDTH - 8, window.getY() + yOffset + 1, -1);
        if(visible) {
            for (Component comp : options) {
                comp.render(context, mouseX, mouseY, delta);
            }
        }
        if (hovered(window.getX(),window.getY()  + yOffset, Constants.WIDTH, Constants.HEIGHT, mouseX, mouseY)) {
            context.fill(0, mc.getWindow().getScaledHeight(), mc.textRenderer.getWidth(module.getDesc()) + 1, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight - 1, Constants.BACKGROUND_COLOR);
            context.drawTextWithShadow(mc.textRenderer, module.getDesc(), 0, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight,  -1);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible) {
            for(Component comp : options){
                comp.mouseClicked(mouseX,mouseY,button);
            }
        }
        if(hovered(window.getX(),window.getY()  + yOffset, Constants.WIDTH, Constants.HEIGHT, (int) mouseX, (int) mouseY)){
            if(button == 0){
                module.toggle();
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
