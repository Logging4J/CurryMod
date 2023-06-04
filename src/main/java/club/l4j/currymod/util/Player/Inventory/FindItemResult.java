package club.l4j.currymod.util.Player.Inventory;

import net.minecraft.util.Hand;

public class FindItemResult {
    private final int slot, count;

    public FindItemResult(int slot, int count) {
        this.slot = slot;
        this.count = count;
    }

    public int getSlot() {
        return slot;
    }

    public int getCount() {
        return count;
    }

    public boolean found() {
        return slot != -1;
    }

    public Hand getHand() {
        if (slot == IventoryUtilHEEDI.OFFHAND) return Hand.OFF_HAND;
        else if (slot == mc.player.getInventory().selectedSlot) return Hand.MAIN_HAND;
        return null;
    }

    public boolean isMainHand() {
        return getHand() == Hand.MAIN_HAND;
    }

    public boolean isOffhand() {
        return getHand() == Hand.OFF_HAND;
    }

    public boolean isHotbar() {
        return slot >= IventoryUtilHEEDI.HOTBAR_START && slot <= IventoryUtilHEEDI.HOTBAR_END;
    }

    public boolean isMain() {
        return slot >= IventoryUtilHEEDI.MAIN_START && slot <= IventoryUtilHEEDI.MAIN_END;
    }

    public boolean isArmor() {
        return slot >= IventoryUtilHEEDI.ARMOR_START && slot <= IventoryUtilHEEDI.ARMOR_END;
    }
}