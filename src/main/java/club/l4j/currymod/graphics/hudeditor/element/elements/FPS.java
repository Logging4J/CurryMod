package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.RenderHudEvent;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;

public class FPS extends HudElement {
    public FPS() {
        super("FPS");
    }

    @DemoListen
    public void onRender(RenderHudEvent e){
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer,"FPS:"+TextUtil.WHITE + mc.getCurrentFps(), 0, mc.getWindow().getScaledHeight() - (mc.textRenderer.fontHeight * 3), CurryMod.uniColor.getRGBA());
    }
}
