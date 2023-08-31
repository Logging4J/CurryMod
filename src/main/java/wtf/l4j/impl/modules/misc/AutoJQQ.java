package wtf.l4j.impl.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.text.Text;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "AutoJQQ", desc = "Log just like jqq >:)", category = Category.MISC)
public class AutoJQQ extends Module implements GameTickListener {

    public OptionSlider health = new OptionSlider("Health",1,20,1,4);

    public AutoJQQ(){
        addOptions(health);
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
        if(mc.player.getHealth() < health.getFloatValue()){
            mc.player.networkHandler.getConnection().disconnect(Text.of("[AutoJQQ] Activated!?!?!?"));
            toggle();
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +health.getIntValue()+TextUtil.GRAY+"]";
    }

}
