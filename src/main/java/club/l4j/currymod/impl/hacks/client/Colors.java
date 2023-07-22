package club.l4j.currymod.impl.hacks.client;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "Colors", description = "Manages colors", category = Hack.Category.CLIENT)
public class Colors extends Hack {

    public static OptionSlider red = new OptionSlider("Red", 0, 255, 1, 255);
    public static OptionSlider green = new OptionSlider("Green", 0, 255, 1, 255);
    public static OptionSlider blue = new OptionSlider("Blue", 0, 255, 1, 255);
    public static OptionSlider alpha = new OptionSlider("Alpha", 0, 255, 1, 255);

    public static OptionBoolean rainbow = new OptionBoolean("Rainbow", true);

    public Colors() {
        addOptions(red, green, blue, alpha, rainbow);
        setEnabled(true);
    }

    @DemoListen
    public void onTick(TickEvent e){
        CurryMod.colorManager.setRed(red.getIntValue());
        CurryMod.colorManager.setGreen(green.getIntValue());
        CurryMod.colorManager.setBlue(blue.getIntValue());
        CurryMod.colorManager.setAlpha(alpha.getIntValue());
    }

}
