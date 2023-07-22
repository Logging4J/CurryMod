package club.l4j.currymod.impl.gui.hudeditor.element.elements;

import club.l4j.currymod.core.event.events.RenderHudEvent;
import club.l4j.currymod.impl.gui.hudeditor.element.HudElement;
import club.l4j.currymod.core.util.render.RenderUtils;
import demo.knight.demobus.event.DemoListen;

public class Logo extends HudElement {

    public Logo() {
        super("Logo");
    }

    @DemoListen
    public void onRender(RenderHudEvent e){
        if(nullCheck()){return;}
        RenderUtils.drawImage(e.getContext(), getX(), getY(), 50, 50, null, "textures/curry.png");
    }

}
