package club.l4j.currymod.impl.gui.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.RenderHudEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.util.render.RenderUtils;
import club.l4j.currymod.impl.gui.hudeditor.element.HudElement;
import club.l4j.currymod.core.util.TextUtil;
import club.l4j.currymod.impl.hacks.client.Colors;
import demo.knight.demobus.event.DemoListen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HackList extends HudElement {
    public HackList() {
        super("HackList");
    }

    @DemoListen
    public void onRender(RenderHudEvent e){
        if(nullCheck()){return;}
        ArrayList<String> list = new ArrayList<>();
        for (Hack hack : CurryMod.hackManager.getEnabledHackFeatures()) {
            list.add(hack.getName() + (hack.getContent() != null ? TextUtil.WHITE + hack.getContent() : ""));
        }
        list.sort(Comparator.comparingInt(mc.textRenderer::getWidth));
        Collections.reverse(list);
        int y = 2;
        for (final String name : list) {
            e.getContext().drawTextWithShadow(mc.textRenderer, name, (mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(name)) - 3, y + 2, Colors.rainbow.isEnabled() ? RenderUtils.rainbow(1, CurryMod.colorManager.getColor()) : CurryMod.colorManager.getRGBA());
            y += 10;
        }
    }
}
