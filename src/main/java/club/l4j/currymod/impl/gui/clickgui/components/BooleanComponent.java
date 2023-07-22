package club.l4j.currymod.impl.gui.clickgui.components;

import club.l4j.currymod.impl.gui.Constants;
import club.l4j.currymod.impl.gui.clickgui.HackButton;
import club.l4j.currymod.core.hack.options.Option;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.util.TextUtil;
import net.minecraft.client.gui.DrawContext;

public class BooleanComponent extends Component{
    public BooleanComponent(Option option, HackButton hackButton, int yOffset) {
        super(option, hackButton, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(hackButton.window.x, hackButton.window.y + hackButton.yOffset + yOffset,hackButton.window.x + Constants.WIDTH,hackButton.window.y + hackButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer,((OptionBoolean) option).isEnabled() ? TextUtil.GREEN + ((OptionBoolean) option).getName() : TextUtil.RED + ((OptionBoolean) option).getName(),hackButton.window.x + 1, hackButton.window.y + hackButton.yOffset + yOffset + 1, -1);

    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(hovered((int) mouseX, (int) mouseY)){
            if(button == 0){
                ((OptionBoolean) option).toggle();
            }
        }
    }
}
