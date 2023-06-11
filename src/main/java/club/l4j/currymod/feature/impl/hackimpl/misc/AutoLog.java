package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.text.Text;

@Hack.Construct(name = "AutoLog", description = "only faggots log", category = Hack.Category.EXPLOITS)
public class AutoLog extends Hack {

    OptionSlider health = new OptionSlider("Health",1,20,1,4);

    public AutoLog(){
        addOptions(health);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck()){return;}
        if(mc.player.getHealth() < health.getFloatValue()){
            mc.player.networkHandler.getConnection().disconnect(Text.of("[AutoJQQ] Activated"));
            toggle();
        }
    }

}
