package dev.l4j.currymod.client.screen.clickgui;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.modules.client.ClickGUI;
import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

//WIP
public class ClickGUIScreen extends Screen {

    @Getter
    private static ClickGUIScreen instance = new ClickGUIScreen();
    private final List<Panel> panels;

    public ClickGUIScreen() {
        super(Text.literal("CurryMod ClickGUI"));
        panels = new ArrayList<>();

        int xOffset = 0;
        for (Module.Category category : Module.Category.values()) {
            Panel panel = new Panel(category, 10 + xOffset, 20, 145, 15);

            panels.add(panel);
            xOffset += (panel.getWidth() + 10);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        panels.forEach(panel -> panel.render(context, mouseX, mouseY, deltaTicks));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        panels.forEach(panel -> panel.keyPressed(keyCode, scanCode, modifiers));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void close() {
        CurryMod.INSTANCE.moduleManager.getModule(ClickGUI.class).toggle();
        super.close();
    }
}
