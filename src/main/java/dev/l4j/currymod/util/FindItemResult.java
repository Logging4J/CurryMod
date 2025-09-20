package dev.l4j.currymod.util;

import net.minecraft.util.Hand;

public record FindItemResult(int slot, int count) implements MinecraftInterface {

    public static final int HOTBAR_START_SLOT = 0;
    public static final int HOTBAR_END_SLOT = 8;
    public static final int OFFHAND_SLOT = 45;
    public static final int MAIN_START_SLOT = 9;
    public static final int MAIN_END_SLOT = 35;
    public static final int ARMOR_START_SLOT = 36;
    public static final int ARMOR_END_SLOT = 39;

    public boolean found() {
        return slot != -1;
    }

    public Hand getHand() {
        if (slot == OFFHAND_SLOT) return Hand.OFF_HAND;
        else if (slot == mc.player.getInventory().getSelectedSlot()) return Hand.MAIN_HAND;
        return null;
    }

    public boolean isMainHand() {
        return getHand() == Hand.MAIN_HAND;
    }


    public boolean isOffhand() {
        return getHand() == Hand.OFF_HAND;
    }


    public boolean isHotbar() {
        return isInRange(slot, HOTBAR_START_SLOT, HOTBAR_END_SLOT);
    }


    public boolean isMain() {
        return isInRange(slot, MAIN_START_SLOT, MAIN_END_SLOT);
    }


    public boolean isArmor() {
        return isInRange(slot, ARMOR_START_SLOT, ARMOR_END_SLOT);
    }


    private boolean isInRange(int slot, int start, int end) {
        return slot >= start && slot <= end;
    }
}
