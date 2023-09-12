package wtf.l4j.api.manager.managers;

import lombok.Getter;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.impl.hudelements.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HudManager {

    private List<HudElement> hudElements;

    public void init() {
        hudElements = new ArrayList<>();
        hudElements.add(new FPS());
        hudElements.add(new ModuleList());
        hudElements.add(new Ping());
        hudElements.add(new Version());
        hudElements.add(new WaterMark());
        hudElements.add(new XYZ());
        hudElements.add(new Welcomer());
        hudElements.add(new Direction());
    }

}
