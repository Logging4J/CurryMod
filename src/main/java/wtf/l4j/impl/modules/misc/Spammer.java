package wtf.l4j.impl.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.PlayerUtils;
import wtf.l4j.api.utils.TimerUtil;
import wtf.l4j.api.utils.text.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO: Finish Spammer
@ModuleInfo(name = "Spammer", desc = "Spam stuff", category = Category.MISC)
public class Spammer extends Module implements GameTickListener {

    private OptionSlider delay = new OptionSlider("Delay",0 ,10 ,0.25 ,2);
    private OptionMode mode = new OptionMode("Mode", "SexScript", "SexScript", "SpikeStinger");
    private final List<String> sexscript = new ArrayList<>(TextUtil.SEX_SCRIPT);
    private final List<String> spike = new ArrayList<>(TextUtil.SPIKE_AND_KIDS);
    private TimerUtil timer = new TimerUtil();
    int index;



    public Spammer(){
        addOptions(delay, mode);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
        index = 0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        index = 0;
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if(nullCheck()) toggle();
        String phrase;
        if(mode.isMode("SexScript")) {
            phrase = sexscript.get(new Random().nextInt(sexscript.size()));
        }else {
            phrase = spike.get(index);
        }

        if(timer.passedS(delay.getValue())) {
            playerMsg(phrase);
            timer.reset();
            index++;
            if(index >= spike.size()){
                index = 0;
            }
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE + mode.getMode() + TextUtil.GRAY+"]";
    }

}
