package club.l4j.currymod.impl.hacks.client;

import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.impl.gui.hudeditor.HudEditorScreen;

@Hack.Construct(name = "HudEditor", description = "Edit Hud", category = Hack.Category.CLIENT)
public class HudEditor extends Hack {

    @Override
    public void onEnable() {
        mc.setScreen(new HudEditorScreen());
        super.onEnable();
    }

}
