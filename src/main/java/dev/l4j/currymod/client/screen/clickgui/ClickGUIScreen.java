package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.client.screen.hud.HudEditorScreen;
import lombok.Getter;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

//WIP
public class ClickGUIScreen extends Screen {

    @Getter
    private static ClickGUIScreen instance = new ClickGUIScreen();

    public ClickGUIScreen() {
        super(Text.literal("CurryMod ClickGUI"));
    }
}
