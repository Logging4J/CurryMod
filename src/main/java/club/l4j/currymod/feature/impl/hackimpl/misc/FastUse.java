package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.mixin.minecraft.IMinecraftClient;
import demo.knight.demobus.event.DemoListen;

@Hack.Construct(name = "FastUse", description = "Use Items quickly", category = Hack.Category.MISC)
public class FastUse extends Hack {

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck()){return;}
        ((IMinecraftClient) mc).setItemUseCooldown(0);
    }
}
