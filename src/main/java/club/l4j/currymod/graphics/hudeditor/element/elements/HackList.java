package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HackList extends HudElement {
    public HackList() {
        super("HackList");
    }

    @DemoListen
    public void onRender(Render2DEvent e){
        if(nullCheck()){return;}
        ArrayList<String> list = new ArrayList<>();
        for (Hack hack : CurryMod.featureManager.getEnabledHackFeatures()) {
            list.add(hack.getName() + (hack.getContent() != null ? TextUtil.WHITE + hack.getContent() : ""));
        }
        list.sort(Comparator.comparingInt(mc.textRenderer::getWidth));
        Collections.reverse(list);
        int y = 2;
        for (final String name : list) {
            e.getContext().drawTextWithShadow(mc.textRenderer, name, (mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(name)) - 3, y + 2, CurryMod.uniColor.getRGBA());
            y += 10;
        }
    }
}
