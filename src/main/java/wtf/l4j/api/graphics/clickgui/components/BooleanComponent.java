package wtf.l4j.api.graphics.clickgui.components;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.ModuleButton;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.utils.text.TextUtil;

public class BooleanComponent extends Component{
    public BooleanComponent(Option option, ModuleButton moduleButton, int yOffset) {
        super(option, moduleButton, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(moduleButton.window.getX(), moduleButton.window.getY() + moduleButton.yOffset + yOffset,moduleButton.window.getX() + Constants.WIDTH,moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        context.drawTextWithShadow(mc.textRenderer,((OptionBoolean) option).isEnabled() ? TextUtil.GREEN + ((OptionBoolean) option).getName() : TextUtil.RED + ((OptionBoolean) option).getName(),moduleButton.window.getX() + 1, moduleButton.window.getY() + moduleButton.yOffset + yOffset + 1, -1);

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
