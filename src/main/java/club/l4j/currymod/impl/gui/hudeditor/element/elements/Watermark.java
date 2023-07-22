package club.l4j.currymod.impl.gui.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.RenderHudEvent;
import club.l4j.currymod.core.util.render.RenderUtils;
import club.l4j.currymod.impl.gui.hudeditor.element.HudElement;
import club.l4j.currymod.core.util.TextUtil;
import club.l4j.currymod.impl.hacks.client.Colors;
import demo.knight.demobus.event.DemoListen;

public class Watermark extends HudElement {

    public Watermark() {
        super("WaterMark");
    }

    @DemoListen
    public void onRender(RenderHudEvent e){
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer, CurryMod.MOD_NAME + TextUtil.WHITE +"b" + CurryMod.VERSION, 0, 1, Colors.rainbow.isEnabled() ? RenderUtils.rainbow(1, CurryMod.colorManager.getColor()) : CurryMod.colorManager.getRGBA());
    }

}
