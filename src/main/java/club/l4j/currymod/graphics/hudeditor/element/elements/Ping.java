package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;

public class Ping extends HudElement {
    public Ping() {
        super("Ping");
    }

    @DemoListen
    public void onRender(Render2DEvent e) {
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer, "Ping:"+TextUtil.WHITE + (mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()) == null ? 0 : mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()).getLatency()), 0, mc.getWindow().getScaledHeight() - (mc.textRenderer.fontHeight * 2), CurryMod.uniColor.getRGBA());
    }
}
