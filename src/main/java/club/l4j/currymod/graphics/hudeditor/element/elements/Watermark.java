package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;

public class Watermark extends HudElement {

    public Watermark() {
        super("WaterMark");
    }

    @DemoListen
    public void onRender(Render2DEvent e){
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer, CurryMod.MOD_NAME + TextUtil.WHITE +"b" + CurryMod.VERSION, 0, 1, CurryMod.uniColor.getRGBA());
    }

}
