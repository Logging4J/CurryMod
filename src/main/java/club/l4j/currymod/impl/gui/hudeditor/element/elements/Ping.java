package club.l4j.currymod.impl.gui.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.RenderHudEvent;
import club.l4j.currymod.core.util.render.RenderUtils;
import club.l4j.currymod.impl.gui.hudeditor.element.HudElement;
import club.l4j.currymod.core.util.TextUtil;
import club.l4j.currymod.impl.hacks.client.Colors;
import demo.knight.demobus.event.DemoListen;

public class Ping extends HudElement {
    public Ping() {
        super("Ping");
    }

    @DemoListen
    public void onRender(RenderHudEvent e) {
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer, "Ping:"+TextUtil.WHITE + (mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()) == null ? 0 : mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()).getLatency()), 0, mc.getWindow().getScaledHeight() - (mc.textRenderer.fontHeight * 2), Colors.rainbow.isEnabled() ? RenderUtils.rainbow(1, CurryMod.colorManager.getColor()) : CurryMod.colorManager.getRGBA());
    }
}
