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

    private OptionSlider delay = new OptionSlider("Delay",0 ,10000 ,10 ,9000);
    private OptionMode mode = new OptionMode("Mode", "SexScript", "SexScript", "SpikeStinger");
    private TimerUtil timer = new TimerUtil();

    public Spammer(){
        addOptions(delay, mode);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if(timer.passedMs(delay.getValue())){
            if(mode.isMode("SexScript")) {
                List<String> phrases = new ArrayList<>(TextUtil.SEX_SCRIPT);
                String randomPhrase = phrases.get(new Random().nextInt(phrases.size()));
                playerMsg(randomPhrase);
            }
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE + mode.getMode() + TextUtil.GRAY+"]";
    }

}
