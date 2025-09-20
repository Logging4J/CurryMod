package dev.l4j.currymod.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lombok.experimental.UtilityClass;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@UtilityClass
public class InventoryUtils implements MinecraftInterface {

    public FindItemResult findInHotbar(Predicate<ItemStack> itemStackPredicate) {
        Optional<FindItemResult> offHandResult = checkHand(mc.player.getOffHandStack(), FindItemResult.OFFHAND_SLOT, itemStackPredicate);
        if (offHandResult.isPresent()) return offHandResult.get();

        Optional<FindItemResult> mainHandResult = checkHand(mc.player.getMainHandStack(), mc.player.getInventory().getSelectedSlot(), itemStackPredicate);
        return mainHandResult.orElseGet(() -> find(itemStackPredicate, 0, 8));

    }


    private Optional<FindItemResult> checkHand(ItemStack stack, int slot, Predicate<ItemStack> isGood) {
        if (isGood.test(stack)) {
            return Optional.of(new FindItemResult(slot, stack.getCount()));
        }
        return Optional.empty();
    }


    public FindItemResult find(Predicate<ItemStack> itemStackPredicate, int start, int end) {
        if (mc.player == null) return new FindItemResult(0, 0);

        int count = 0;
        int slot = -1;

        for (int i = start; i <= end; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (itemStackPredicate.test(stack)) {
                if (slot == -1) slot = i;
                count += stack.getCount();
            }
        }
        return new FindItemResult(slot, count);
    }

    public void swap(int slot) {
        if (slot == FindItemResult.OFFHAND_SLOT) return;
        if (slot < 0 || slot > 8) return;
        if (mc.player.getInventory().getSelectedSlot() == slot) return;

        mc.player.getInventory().setSelectedSlot(slot);
        mc.getNetworkHandler().sendPacket(new UpdateSelectedSlotC2SPacket(slot));
    }

    public void windowClick(ScreenHandler container, int slot, SlotActionType action, int clickData) {
        mc.interactionManager.clickSlot(container.syncId, slot, clickData, action, mc.player);
    }

    public static Object2IntMap<RegistryEntry<Enchantment>> getEnchantments(ItemStack stack) {
        Object2IntMap<RegistryEntry<Enchantment>> enchantments = new Object2IntOpenHashMap<>();

        Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> entries =
                stack.getItem() == Items.ENCHANTED_BOOK
                        ? stack.getOrDefault(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
                        .getEnchantmentEntries()
                        : stack.getEnchantments().getEnchantmentEntries();

        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : entries) {
            enchantments.put(entry.getKey(), entry.getIntValue());
        }

        return enchantments;
    }

    public static int getEnchantmentLevel(ItemStack stack, RegistryKey<Enchantment> enchantmentKey) {
        if (stack.isEmpty()) return 0;

        return getEnchantmentLevel(getEnchantments(stack), enchantmentKey);
    }

    public static int getEnchantmentLevel(Object2IntMap<RegistryEntry<Enchantment>> itemEnchantments, RegistryKey<Enchantment> enchantment) {
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantments.object2IntEntrySet()) {
            if (entry.getKey().matchesKey(enchantment)) return entry.getIntValue();
        }
        return 0;
    }
}
