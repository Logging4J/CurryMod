package wtf.l4j.api.manager;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import wtf.l4j.api.manager.managers.*;
import wtf.l4j.api.utils.Config;

public class Managers {

    @Getter private final CommandManager commandManager = new CommandManager();
    @Getter private final FriendManager friendManager = new FriendManager();
    @Getter private final HudManager hudManager = new HudManager();
    @Getter private final ModuleManager moduleManager = new ModuleManager();
    @Getter private final ColorManager colorManager = new ColorManager();

    public Managers(){
        commandManager.init();
        friendManager.init();
        hudManager.init();
        moduleManager.init();
    }


}
