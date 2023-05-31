package club.l4j.currymod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class InventoryUtil {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static int getSlot(Item items) {
        int slot = -1;
        for (int i = 0; i < 9; i++) {
            Item item = mc.player.getInventory().getStack(i).getItem();
            if (item == items) {
                slot = i;
                break;
            }
        }
        return slot;
    }

    public static void moveToOffhand(int slot) {
        boolean isHolding = mc.player.getOffHandStack().getItem() != Items.AIR;
        mc.interactionManager.clickSlot( mc.player.currentScreenHandler.syncId, slot < 9 ? slot + 36 : slot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
        if (isHolding) {
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot < 9 ? slot + 36 : slot, 0, SlotActionType.PICKUP, mc.player);
        }
    }
}
