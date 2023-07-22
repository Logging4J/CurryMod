package club.l4j.currymod.impl.gui.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.RenderHudEvent;
import club.l4j.currymod.core.util.render.RenderUtils;
import club.l4j.currymod.impl.gui.hudeditor.element.HudElement;
import club.l4j.currymod.core.util.TextUtil;
import club.l4j.currymod.impl.hacks.client.Colors;
import demo.knight.demobus.event.DemoListen;

public class Coords extends HudElement {
    public Coords() {
        super("Coords");
    }

    @DemoListen
    public void onRender(RenderHudEvent e){
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer ,"XYZ:" + TextUtil.WHITE + Math.round(mc.player.getX() * 100.0) / 100.0 + " ," + Math.round(mc.player.getY() * 100.0) / 100.0 + " ," + Math.round(mc.player.getZ() * 100.0) / 100.0, 0, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight, Colors.rainbow.isEnabled() ? RenderUtils.rainbow(1, CurryMod.colorManager.getColor()) : CurryMod.colorManager.getRGBA());
    }
}
