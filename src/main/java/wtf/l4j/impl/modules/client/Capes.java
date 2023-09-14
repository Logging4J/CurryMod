package wtf.l4j.impl.modules.client;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;

@ModuleInfo(name = "Capes", desc = "Cosmetic", category = Category.CLIENT)
public class Capes extends Module {
    public static OptionMode mode = new OptionMode("cape", "cape1", "cape1", "cape2", "cape3");

    public Capes() {
        addOptions(mode);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

