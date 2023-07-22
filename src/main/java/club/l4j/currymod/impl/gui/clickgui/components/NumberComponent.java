package club.l4j.currymod.impl.gui.clickgui.components;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.impl.gui.clickgui.HackButton;
import club.l4j.currymod.core.hack.options.Option;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import club.l4j.currymod.impl.gui.Constants;
import net.minecraft.client.gui.DrawContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberComponent extends Component{

    private boolean sliding = false;

    public NumberComponent(Option option, HackButton hackButton, int yOffset) {
        super(option, hackButton, yOffset);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(hackButton.window.x, hackButton.window.y + hackButton.yOffset + yOffset,hackButton.window.x + Constants.WIDTH,hackButton.window.y + hackButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);

        double diff = Math.min(Constants.WIDTH,Math.max(0,mouseX - hackButton.window.x));
        int renderWidth = (int) (Constants.WIDTH * (((OptionSlider) option).getValue() - ((OptionSlider) option).getMin()) / (((OptionSlider) option).getMax() - ((OptionSlider) option).getMin()));

        if(sliding){
            if(diff == 0){
                ((OptionSlider) option).setValue(((OptionSlider) option).getMin());
            }else{
                ((OptionSlider) option).setValue(roundToPlace(((diff/ Constants.WIDTH) * (((OptionSlider)option).getMax() - ((OptionSlider) option).getMin()) + ((OptionSlider) option).getMin()), 2));
            }
        }

        context.fill(hackButton.window.x, hackButton.window.y + hackButton.yOffset + yOffset + Constants.HEIGHT - 1,hackButton.window.x + renderWidth,hackButton.window.y + hackButton.yOffset + yOffset + Constants.HEIGHT, CurryMod.colorManager.getRGBA());

        context.drawTextWithShadow(mc.textRenderer, option.getName() + " : " + roundToPlace(((OptionSlider) option).getValue(),2),hackButton.window.x + 1, hackButton.window.y + hackButton.yOffset + yOffset + 1, -1);
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
