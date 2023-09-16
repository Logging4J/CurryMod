package wtf.l4j.impl.modules.misc;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.module.option.options.OptionSlider;

@ModuleInfo(name = "ViewLock", desc = "Locks View", category = Category.MISC)
public class ViewLock extends Module implements GameTickListener {

    private OptionBoolean yaw = new OptionBoolean("Yaw", true);
    private OptionSlider yawSlider = new OptionSlider("YawSlider", -180, 180, 1, 90);

    private OptionBoolean pitch = new OptionBoolean("Pitch", true);
    private OptionSlider pitchSlider = new OptionSlider("PitchSlider", -90, 90, 1, 67);

    public ViewLock(){
        addOptions(yaw, yawSlider, pitch, pitchSlider);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onGameTick() {
        if(pitch.isEnabled()) mc.player.setPitch(pitchSlider.getFloatValue());
        if(yaw.isEnabled()) mc.player.setYaw(yawSlider.getFloatValue());
    }
}
