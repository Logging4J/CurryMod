package club.l4j.currymod.graphics.clickgui.components;

import club.l4j.currymod.graphics.Common;
import club.l4j.currymod.graphics.clickgui.HackButton;
import club.l4j.currymod.feature.options.Option;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.util.TextUtil;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class BooleanComponent extends Component{
    public BooleanComponent(Option option, HackButton hackButton, int yOffset) {
        super(option, hackButton, yOffset);
    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices,hackButton.window.x, hackButton.window.y + hackButton.yOffset + yOffset,hackButton.window.x + Common.WIDTH,hackButton.window.y + hackButton.yOffset + yOffset + Common.HEIGHT, Common.BACKGROUND_COLOR);
        tr.drawWithShadow(matrices,((OptionBoolean) option).isEnabled() ? TextUtil.GREEN + ((OptionBoolean) option).getName() : TextUtil.RED + ((OptionBoolean) option).getName(),hackButton.window.x + 1, hackButton.window.y + hackButton.yOffset + yOffset + 1, -1);

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
