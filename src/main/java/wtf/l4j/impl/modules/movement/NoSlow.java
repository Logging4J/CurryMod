package wtf.l4j.impl.modules.movement;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.client.network.PendingUpdateManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.Hand;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.utils.text.TextUtil;
import wtf.l4j.mixin.accessors.ClientWorldAccessor;

@ModuleInfo(name = "NoSlow", desc = "NoSlowin", category = Category.MOVEMENT)
public class NoSlow extends Module implements GameTickListener {

    OptionMode mode = new OptionMode("Mode", "Vanilla", "Vanilla", "2b2t");

    public NoSlow(){
        addOptions(mode);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        super.onDisable();
    }


    @Override
    public void onGameTick() {
        if(nullCheck() || mc.player.getActiveHand() == null) return;
        if(mode.isMode("2b2t")) {
            switch (mc.player.getActiveHand()) {
                case MAIN_HAND -> sendPacket(new PlayerInteractItemC2SPacket(Hand.OFF_HAND, getWorldActionId(mc.world)));
                case OFF_HAND -> {
                    sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot % 8 + 1));
                    sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot));
                }
            }
        }
    }

    public static int getWorldActionId(ClientWorld world) {
        PendingUpdateManager pum = ((ClientWorldAccessor) world).acquirePendingUpdateManager();
        int p = pum.getSequence();
        pum.close();
        return p;
    }

    @Override
    public String getContent() {
        return TextUtil.GRAY +"["+ TextUtil.WHITE +mode.getMode()+TextUtil.GRAY+"]";
    }

}