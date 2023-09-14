package wtf.l4j.impl.modules.visual;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.utils.world.FakePlayerEntity;

@ModuleInfo(name = "FakePlayer", desc = "test mods", category = Category.VISUAL)
public class FakePlayer extends Module {

    private FakePlayerEntity fakePlayerEntity;

    @Override
    public void onEnable() {
        fakePlayerEntity = new FakePlayerEntity();
        fakePlayerEntity.add();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if(fakePlayerEntity != null) {
            fakePlayerEntity.remove();
        }
        super.onDisable();
    }
}
