package club.l4j.currymod.impl.hacks.visual;

import club.l4j.currymod.core.event.events.PacketReceiveEvent;
import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionMode;
import club.l4j.currymod.core.hack.options.impl.OptionSlider;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

@Hack.Construct(name = "Atmosphere", description = "Changes the sky", category = Hack.Category.VISUAL)
public class Atmosphere extends Hack {

    public OptionSlider time = new OptionSlider("Time", 0, 24, 0.1, 12);
    public OptionMode mode = new OptionMode("Mode", "Rain", "Clear" ,"Rain");

    public Atmosphere() {
        addOptions(time, mode);
    }

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()) return;
        mc.world.setTimeOfDay(time.getLongValue() * 1000);
        if(mode.isMode("Rain")){
            mc.world.getLevelProperties().setRaining(true);
            mc.world.setRainGradient(2);
        }else {
            mc.world.getLevelProperties().setRaining(false);
            mc.world.setRainGradient(0);
        }
    }

    @DemoListen
    public void onPacketReceive(PacketReceiveEvent e) {
        if(e.getPacket() instanceof WorldTimeUpdateS2CPacket){
            e.setCanceled(true);
        } else if (e.getPacket() instanceof GameStateChangeS2CPacket p) {
            if(p.getReason() == GameStateChangeS2CPacket.RAIN_STARTED || p.getReason()  == GameStateChangeS2CPacket.RAIN_STOPPED || p.getReason() == GameStateChangeS2CPacket.RAIN_GRADIENT_CHANGED || p.getReason() == GameStateChangeS2CPacket.THUNDER_GRADIENT_CHANGED){
                e.setCanceled(true);
            }
        }
    }
}
