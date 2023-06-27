package club.l4j.currymod.feature.impl.hackimpl.client;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.hudeditor.HudEditorScreen;

@Hack.Construct(name = "HudEditor", description = "Edit Hud", category = Hack.Category.CLIENT)
public class HudEditor extends Hack {

    @Override
    public void onEnable() {
        mc.setScreen(new HudEditorScreen());
        super.onEnable();
    }

}
