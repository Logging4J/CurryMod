package dev.l4j.currymod.client.hud.elements;

import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.hud.HudElement;
import dev.l4j.currymod.client.module.Module;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@HudElement.Info(name = "ModuleList", description = "List of active modules", defaultX = 10, defaultY = 10)
public class ModuleListElement extends HudElement {

    @Override
    public void render(DrawContext context) {
        List<Module> enabledModules = CurryMod.INSTANCE.moduleManager.getEnabledModules();
        height = mc.textRenderer.fontHeight * enabledModules.size();

        boolean flipVertical = y + height > (mc.getWindow().getScaledHeight() / scale) / 2;
        if (flipVertical) {
            enabledModules.sort(Comparator.comparingInt(m -> mc.textRenderer.getWidth(m.getDisplayName())));
        } else {
            enabledModules.sort(Comparator.comparingInt((Module m) -> mc.textRenderer.getWidth(m.getDisplayName())).reversed());
        }

        String longest = enabledModules.isEmpty() ? "" : flipVertical ?  enabledModules.getLast().getDisplayName() : enabledModules.getFirst().getDisplayName();
        width = mc.textRenderer.getWidth(longest);

        int yOffset = 0;
        boolean alignRight = x + width > (mc.getWindow().getScaledWidth() / scale) / 2;

        for (Module module : enabledModules) {
            int drawX = alignRight ? x + width - mc.textRenderer.getWidth(module.getDisplayName()) : x;
            context.drawTextWithShadow(mc.textRenderer, module.getDisplayName(), drawX, y + yOffset, -1);
            yOffset += mc.textRenderer.fontHeight;
        }
    }
}
