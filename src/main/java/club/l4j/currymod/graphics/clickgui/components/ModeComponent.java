package club.l4j.currymod.graphics.clickgui.components;

import club.l4j.currymod.graphics.clickgui.HackButton;
import club.l4j.currymod.feature.options.Option;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.graphics.Constants;
import net.minecraft.client.gui.DrawContext;

public class ModeComponent extends Component{
    public ModeComponent(Option option, HackButton hackButton, int yOffset) {
        super(option, hackButton, yOffset);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(hackButton.window.x, hackButton.window.y + hackButton.yOffset + yOffset,hackButton.window.x + Constants.WIDTH,hackButton.window.y + hackButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer, ((OptionMode) option).getName() + " : " + ((OptionMode) option).getMode(),hackButton.window.x + 1, hackButton.window.y + hackButton.yOffset + yOffset + 1, -1);

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
