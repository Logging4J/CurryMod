package dev.logging4j.currymod.module.modules.fun;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.listener.IPrePlayerTickListener;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionNumber;

@Module.Info(name = "Twerk", description = "TudouCat Twerk", category = Module.Category.FUN)
public class Twerk extends Module implements IPrePlayerTickListener {

    private final OptionNumber<Integer> delay = new OptionNumber<>("Delay", 5, 1, 20, 1);

    public Twerk() {
        addOptions(delay);
    }

    int count = 0;

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(PrePlayerTickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(PrePlayerTickEvent.ID, this);
    }


    @Override
    public void onPrePlayerTick() {
        if (count > delay.getValue()) {
            mc.options.sneakKey.setPressed(true);
            count = 0;
        } else {
            mc.options.sneakKey.setPressed(false);
            count++;
        }
    }
}
