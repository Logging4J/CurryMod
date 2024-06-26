package wtf.l4j.api.graphics.hudeditor;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.MinecraftInterface;
import wtf.l4j.impl.modules.client.HudEditor;

public class HudEditorScreen extends Screen implements MinecraftInterface {
    private Panel panel;

    public HudEditorScreen() {
        super(Text.of("HudEditor"));
        panel = new Panel(10, 10);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if(HudEditor.noGradient.isEnabled()) {
            context.fillGradient(mc.getWindow().getScaledWidth(), mc.getWindow().getScaledHeight(), 0, 0, CurryMod.getInstance().getManagers().getColorManager().getRGBA(), Integer.MIN_VALUE);
        }
        panel.render(context,mouseX,mouseY,delta);
        panel.updatePosition(mouseX,mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        panel.mouseClicked(mouseX,mouseY,button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        panel.mouseReleased(mouseX,mouseY,button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        CurryMod.getInstance().getManagers().getModuleManager().getModule(HudEditor.class).toggle();
        super.close();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
