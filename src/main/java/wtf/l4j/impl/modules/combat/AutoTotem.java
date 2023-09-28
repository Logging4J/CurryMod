package wtf.l4j.impl.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.DeathListener;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
    @ModuleInfo(name = "TotemPopCounter", desc = "Counts the amount of totem pops a player has had in chat", category = Category.COMBAT)
    public class AutoTotem extends Module implements GameTickListener {


        public AutoTotem() {
        }

        @Override
        public void onEnable() {
            DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        }

        @Override
        public void onDisable() {
            DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        }

        @Override
        public void onGameTick() {

        }
    }