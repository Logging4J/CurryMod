package wtf.l4j.impl.modules.client;

import wtf.l4j.CurryMod;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "DiscordRPC", desc = "discord rpc", category = Category.CLIENT)
public class DiscordRPC extends Module {

    @Override
    public void onEnable() {
        CurryMod.getInstance().getDiscordRP().start();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        CurryMod.getInstance().getDiscordRP().stop();
        super.onDisable();
    }
}
