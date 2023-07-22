package club.l4j.currymod.impl.gui.hudeditor;

import club.l4j.currymod.CurryMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class HudEditorScreen extends Screen {

    private Window window;

    public HudEditorScreen() {
        super(Text.of("HudEditor"));
        window = new Window(10, 10);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        window.render(context,mouseX,mouseY,delta);
        window.updatePosition(mouseX,mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        window.mouseClicked(mouseX,mouseY,button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        window.mouseReleased(mouseX,mouseY,button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        CurryMod.hackManager.getHack("HudEditor").toggle();
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}
