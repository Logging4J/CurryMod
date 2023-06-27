package club.l4j.currymod.graphics.hudeditor.element;

import club.l4j.currymod.graphics.hudeditor.element.elements.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    @Getter
    private List<HudElement> hudElements = new ArrayList<>();

    public HudManager(){
        addHudElement(new Coords());
        addHudElement(new FPS());
        addHudElement(new HackList());
        addHudElement(new Logo());
        addHudElement(new Ping());
        addHudElement(new Watermark());
        addHudElement(new Welcomer());
    }

    private void addHudElement(HudElement hudElement){
        hudElements.add(hudElement);
    }
}
