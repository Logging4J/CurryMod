package wtf.l4j.api.graphics.clickgui;

import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.components.Component;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.utils.IGlobals;

import java.util.ArrayList;
import java.util.List;

import static wtf.l4j.api.graphics.Constants.*;

@Getter
public class Window implements IGlobals {

    private List<ModuleButton> modules;
    private Category category;
    private int x,y,dragX,dragY;
    private boolean visible, dragging;

    public Window(Category category, int x, int y){
        this.category = category;
        this.x = x;
        this.y = y;
        modules = new ArrayList<>();

        visible = true;
        dragging = false;

        int offset = Constants.HEIGHT;

        for(Module module : Managers.getModuleManager().getModulesInCategory(category).get()){
            modules.add(new ModuleButton(this, module, offset));
            offset += Constants.HEIGHT;
        }

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(x, y, x + WIDTH, y + HEIGHT, Managers.getColorManager().getRGBA(), Managers.getColorManager().getRGBA());

        context.drawTextWithShadow(mc.textRenderer, category.getName(), WIDTH / 2 - (mc.textRenderer.getWidth(category.name()) / 2) + x, y + 1, -1);
        context.drawTextWithShadow(mc.textRenderer, visible ? "∨" : "∧",x  + WIDTH - 8, y + 1, -1);

        if(visible){
            for (ModuleButton moduleButton : modules) {
                moduleButton.render(context,mouseX,mouseY,delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(visible){
            for (ModuleButton moduleButton : modules) {
                moduleButton.mouseClicked(mouseX,mouseY,button);
            }
        }
        if(hovered(x,y,  WIDTH,  HEIGHT, (int) mouseX, (int) mouseY)) {
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

        for(ModuleButton mb : modules){
            mb.mouseReleased(mouseX,mouseY,button);
        }
    }
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        for (ModuleButton moduleButton : modules) {
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
        int offset = HEIGHT;

        for(ModuleButton button : modules) {
            button.yOffset = offset;
            offset += HEIGHT;
            if(button.visible) {
                for(Component comp : button.options) {
                    offset+= HEIGHT;
                }
            }
        }
    }

    public static boolean hovered(int x ,int y ,int width, int height,int mouseX ,int mouseY ) {
        return mouseX > x  && mouseX< x  + width && mouseY > y && mouseY < y + height;
    }
}
