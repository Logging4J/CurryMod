package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;

public class Coords extends HudElement {
    public Coords() {
        super("Coords");
    }

    @DemoListen
    public void onRender(Render2DEvent e){
        if(nullCheck()){return;}
        e.getContext().drawTextWithShadow(mc.textRenderer, TextUtil.AQUA + "XYZ:" + TextUtil.WHITE + Math.round(mc.player.getX() * 100.0) / 100.0 + " ," + Math.round(mc.player.getY() * 100.0) / 100.0 + " ," + Math.round(mc.player.getZ() * 100.0) / 100.0, 0, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight, -1);

    }
}
