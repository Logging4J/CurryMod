package wtf.l4j.api.graphics.clickgui.components;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.ModuleButton;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.module.option.options.OptionSlider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberComponent extends Component{

    private boolean sliding = false;

    public NumberComponent(Option option, ModuleButton moduleButton, int yOffset) {
        super(option, moduleButton, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(moduleButton.window.getX(), moduleButton.window.getY() + moduleButton.yOffset + yOffset,moduleButton.window.getX() + Constants.WIDTH,moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);

        double diff = Math.min(Constants.WIDTH,Math.max(0,mouseX - moduleButton.window.getX()));
        int renderWidth = (int) (Constants.WIDTH * (((OptionSlider) option).getValue() - ((OptionSlider) option).getMin()) / (((OptionSlider) option).getMax() - ((OptionSlider) option).getMin()));

        if(sliding){
            if(diff == 0){
                ((OptionSlider) option).setValue(((OptionSlider) option).getMin());
            }else{
                ((OptionSlider) option).setValue(roundToPlace(((diff/ Constants.WIDTH) * (((OptionSlider)option).getMax() - ((OptionSlider) option).getMin()) + ((OptionSlider) option).getMin()), 2));
            }
        }

        context.fill(moduleButton.window.getX(), moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT - 1,moduleButton.window.getX() + renderWidth,moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT, Managers.getColorManager().getRGBA());
        context.drawTextWithShadow(mc.textRenderer, option.getName() + " : " + roundToPlace(((OptionSlider) option).getValue(),2),moduleButton.window.getX() + 1, moduleButton.window.getY() + moduleButton.yOffset + yOffset + 1, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(hovered((int) mouseX, (int) mouseY)){
            if(button == 0){
                sliding = true;
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
    }

    public double roundToPlace(double value, int place){
        if(place < 0){
            return value;
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }
}
