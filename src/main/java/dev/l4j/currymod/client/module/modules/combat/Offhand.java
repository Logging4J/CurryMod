package dev.l4j.currymod.client.module.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.client.module.option.options.OptionBoolean;
import dev.l4j.currymod.client.module.option.options.OptionMode;
import dev.l4j.currymod.client.module.option.options.OptionNumber;
import dev.l4j.currymod.listener.IGameTickListener;
import dev.l4j.currymod.util.FindItemResult;
import dev.l4j.currymod.util.InventoryUtils;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.SlotActionType;

@Module.Info(name = "Offhand", description = "Insane pvp module!", category = Module.Category.COMBAT)
public class Offhand extends Module implements IGameTickListener {

    private final OptionMode mode = new OptionMode("Mode", "Totem", "Totem", "Crystal", "Gapple", "Shield", "Crapple");
    private final OptionNumber<Integer> health = new OptionNumber<>("Health", 1,20,1,16);
    private final OptionBoolean swordGap = new OptionBoolean("SwordGap", true);

    public Offhand() {
        addOptions(mode, health, swordGap);
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(GameTickEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(GameTickEvent.ID, this);
    }

    @Override
    public void onGameTick() {
        if (nullCheck() || mc.currentScreen != null) return;

        if (!mc.player.getOffHandStack().isEmpty() && mc.player.getOffHandStack().getItem() == this.getOffhandItem()) return;

        FindItemResult offhandItem = InventoryUtils.findInHotbar(itemStack -> itemStack.getItem() == this.getOffhandItem());

        if (offhandItem.found()) {
            int slot = offhandItem.slot();
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
        }
    }

    @Override
    public String getDisplayInfo() {
        return mode.getValue();
    }

    private Item getOffhandItem() {
        if (mc.player.getHealth() <= health.getValue()) return Items.TOTEM_OF_UNDYING;
        if (mc.player.getMainHandStack().isIn(ItemTags.SWORDS) && swordGap.getValue()) return Items.ENCHANTED_GOLDEN_APPLE;
        return switch (mode.getValue()) {
            case "Crystal" -> Items.END_CRYSTAL;
            case "Gapple" -> Items.ENCHANTED_GOLDEN_APPLE;
            case "Shield" -> Items.SHIELD;
            case "Crapple" -> Items.GOLDEN_APPLE;
            default -> Items.TOTEM_OF_UNDYING;
        };
    }
}
