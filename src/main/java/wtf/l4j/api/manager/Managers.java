package wtf.l4j.api.manager;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import wtf.l4j.api.manager.managers.*;
import wtf.l4j.api.utils.Config;

@UtilityClass
public class Managers {

    @Getter private CommandManager commandManager = new CommandManager();
    @Getter private FriendManager friendManager = new FriendManager();
    @Getter private HudManager hudManager = new HudManager();
    @Getter private ModuleManager moduleManager = new ModuleManager();
    @Getter private ColorManager colorManager = new ColorManager();


    public void init(){
        commandManager.init();
        friendManager.init();
        hudManager.init();
        moduleManager.init();
    }
}
