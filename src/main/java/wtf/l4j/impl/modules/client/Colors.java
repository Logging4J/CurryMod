package wtf.l4j.impl.modules.client;

import wtf.l4j.api.event.Event;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.event.events.TickEvent;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;

@ModuleInfo(name = "Colors", desc = "Client colors", category = Category.CLIENT)
public class Colors extends Module {

    public static OptionSlider red = new OptionSlider("Red", 0, 255, 1, 135);
    public static OptionSlider green = new OptionSlider("Green", 0, 255, 1, 140);
    public static OptionSlider blue = new OptionSlider("Blue", 0, 255, 1, 255);

    public Colors() {
        addOptions(red, green, blue);
        setEnabled(true);
    }
}
