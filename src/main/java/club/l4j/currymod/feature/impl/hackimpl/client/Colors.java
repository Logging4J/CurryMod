package club.l4j.currymod.feature.impl.hackimpl.client;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Colors", description = "Manages colors", category = Hack.Category.CLIENT)
public class Colors extends Hack {

    public OptionSlider red = new OptionSlider("Red", 0, 255, 1, 255);
    public OptionSlider green = new OptionSlider("Green", 0, 255, 1, 255);
    public OptionSlider blue = new OptionSlider("Blue", 0, 255, 1, 255);
    public OptionSlider alpha = new OptionSlider("Alpha", 0, 255, 1, 255);

    public Colors() {
        addOptions(red, green, blue, alpha);
    }

    @DemoListen
    public void onTick(TickEvent e){
        CurryMod.uniColor.setRed(red.getIntValue());
        CurryMod.uniColor.setGreen(green.getIntValue());
        CurryMod.uniColor.setBlue(blue.getIntValue());
        CurryMod.uniColor.setAlpha(alpha.getIntValue());
    }

}
