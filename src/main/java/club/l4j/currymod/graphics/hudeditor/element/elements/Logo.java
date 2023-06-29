package club.l4j.currymod.graphics.hudeditor.element.elements;

import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.graphics.hudeditor.element.HudElement;
import club.l4j.currymod.util.render.RenderUtils;
import demo.knight.demobus.event.DemoListen;

public class Logo extends HudElement {

    public Logo() {
        super("Logo");
    }

    @DemoListen
    public void onRender(Render2DEvent e){
        if(nullCheck()){return;}
        RenderUtils.drawImage(e.getContext(), getX(), getY(), 50, 50, null, "textures/curry.png");
    }

}