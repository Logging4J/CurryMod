package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.utils.text.TextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@HudElementInfo(name = "ModuleList")
public class ModuleList extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        ArrayList<String> list = new ArrayList<>();
        for (Module module : Managers.getModuleManager().getEnabledModules().get()) {
            list.add(module.getName() + (module.getContent() != null ? " " + TextUtil.WHITE + module.getContent() : ""));
        }
        list.sort(Comparator.comparingInt(mc.textRenderer::getWidth));
        Collections.reverse(list);
        int y = 2;
        for (final String name : list) {
            context.drawTextWithShadow(mc.textRenderer, name, (mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(name)) - 1, y + 2, Managers.getColorManager().getRGBA());
            y += 10;
        }
    }
}
