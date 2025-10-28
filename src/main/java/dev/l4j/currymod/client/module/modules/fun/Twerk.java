package dev.l4j.currymod.client.module.modules.fun;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import dev.l4j.currymod.listener.IGameTickListener;

@Module.Info(name = "Twerk", description = "TudouCat Twerk", category = Module.Category.FUN)
public class Twerk extends Module implements IGameTickListener {

    private final OptionNumber<Integer> delay = new OptionNumber<>("Delay", 5, 1, 20, 1);

    public Twerk() {
        addOptions(delay);
    }

    int count = 0;

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
    }

    @Override
    public void onGameTick() {
        if (count > delay.getValue()) {
            mc.options.sneakKey.setPressed(true);
            count = 0;
        } else {
            mc.options.sneakKey.setPressed(false);
            count++;
        }
    }
}
