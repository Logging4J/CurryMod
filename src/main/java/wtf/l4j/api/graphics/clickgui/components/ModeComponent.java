package wtf.l4j.api.graphics.clickgui.components;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.ModuleButton;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.module.option.options.OptionMode;

public class ModeComponent extends Component{
    public ModeComponent(Option option, ModuleButton moduleButton, int yOffset) {
        super(option, moduleButton, yOffset);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(moduleButton.window.getX(), moduleButton.window.getY() + moduleButton.yOffset + yOffset,moduleButton.window.getX() + Constants.WIDTH,moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer, ((OptionMode) option).getName() + " : " + ((OptionMode) option).getMode(),moduleButton.window.getX() + 1, moduleButton.window.getY() + moduleButton.yOffset + yOffset + 1, -1);

    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(hovered((int) mouseX, (int) mouseY)){
            if(button == 0){
                int i = 0;
                int enumIndex = 0;
                for (String enumName : ((OptionMode) option).getModes()) {
                    if (enumName.equals(((OptionMode) option).getMode())) enumIndex = i;
                    i++;
                }
                if (enumIndex == ((OptionMode) option).getModes().size() - 1) {
                    ((OptionMode)option).setMode(((OptionMode) option).getModes().get(0));
                } else {
                    enumIndex++;
                    i = 0;
                    for (String enumName : ((OptionMode) option).getModes()) {
                        if (i == enumIndex) ((OptionMode)option).setMode(enumName);
                        i++;
                    }
                }
            } else if (button == 1) {
                int i = 0;
                int enumIndex = 0;
                for (String enumName : ((OptionMode) option).getModes()) {
                    if (enumName.equals(((OptionMode) option).getMode())) enumIndex = i;
                    i++;
                }
                if (enumIndex == 0) {
                    ((OptionMode) option).setMode(((OptionMode) option).getModes().get(((OptionMode)option).getModes().size() - 1));
                } else {
                    enumIndex--;
                    i = 0;
                    for (String enumName : ((OptionMode) option).getModes()) {
                        if (i == enumIndex) ((OptionMode) option).setMode(enumName);
                        i++;
                    }
                }
            }
        }
    }
}
