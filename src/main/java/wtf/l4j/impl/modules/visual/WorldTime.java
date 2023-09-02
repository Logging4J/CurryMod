package wtf.l4j.impl.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.utils.text.TextUtil;

@ModuleInfo(name = "WorldTime", desc = "Change world time", category = Category.VISUAL)
public class WorldTime extends Module implements GameTickListener, PacketListener {

    private final OptionSlider time = new OptionSlider("Time", 0, 24, 0.1, 12);
    private long prevTime;

    public WorldTime(){
        addOptions(time);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
        prevTime = mc.world.getTimeOfDay();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        DietrichEvents2.global().unsubscribe(PacketListener.PacketEvent.ID, this);
        mc.world.setTimeOfDay(prevTime);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        mc.world.setTimeOfDay(time.getLongValue() * 1000);
    }

    @Override
    public void onPacket(PacketEvent packetEvent) {
        if(packetEvent.getType() == Type.INCOMING){
            if(packetEvent.getPacket() instanceof WorldTimeUpdateS2CPacket){
                packetEvent.setCancelled(true);
            } else if (packetEvent.getPacket() instanceof GameStateChangeS2CPacket p) {
                if(p.getReason() == GameStateChangeS2CPacket.RAIN_STARTED || p.getReason()  == GameStateChangeS2CPacket.RAIN_STOPPED || p.getReason() == GameStateChangeS2CPacket.RAIN_GRADIENT_CHANGED || p.getReason() == GameStateChangeS2CPacket.THUNDER_GRADIENT_CHANGED){
                    packetEvent.setCancelled(true);
                }
            }
        }
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +time.getIntValue()+TextUtil.GRAY+"]";
    }

}
