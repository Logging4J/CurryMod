package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.RenderHudEvent;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;

public class Welcomer extends HudElement {

    public Welcomer() {
        super("Welcomer");
    }

    @DemoListen
    public void onRender(RenderHudEvent e){
        if(nullCheck()){return;}
        String text =  "Welcome" + TextUtil.WHITE + mc.player.getGameProfile().getName();
        e.getContext().drawTextWithShadow(mc.textRenderer, "Welcome " + TextUtil.WHITE + mc.player.getGameProfile().getName(), mc.getWindow().getScaledWidth() / 2 - (mc.textRenderer.getWidth(text) / 2), 1, CurryMod.uniColor.getRGBA());

    }

}
