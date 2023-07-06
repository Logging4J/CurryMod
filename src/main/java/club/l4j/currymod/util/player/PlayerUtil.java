package club.l4j.currymod.util.player;

import club.l4j.currymod.util.IGlobals;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

import java.util.Set;

public class PlayerUtil implements IGlobals {

    public static final Set<Item> THROWABLES = Sets.newHashSet(
            Items.SNOWBALL, Items.EXPERIENCE_BOTTLE, Items.EGG, Items.SPLASH_POTION, Items.ENDER_PEARL
    );

    public static final Set<Block> ORE_BLOCKS = Sets.newHashSet(
            Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.LAPIS_ORE, Blocks.DIAMOND_ORE,
            Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.COPPER_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DEEPSLATE_COPPER_ORE, Blocks.NETHER_QUARTZ_ORE, Blocks.ANCIENT_DEBRIS
    );

    public static int findHotbarItem(Item item) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    public static void swap(int slot){
        boolean inHand = !mc.player.getInventory().getMainHandStack().isEmpty();
        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 36 + mc.player.getInventory().selectedSlot, 0, SlotActionType.PICKUP, mc.player);
        if(inHand){
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
        }
    }

}
