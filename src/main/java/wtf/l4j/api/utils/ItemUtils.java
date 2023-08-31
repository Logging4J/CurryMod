package wtf.l4j.api.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

@UtilityClass
public class ItemUtils implements IGlobals{

    public int getSlot(Item item) {
        int slot = -1;
        for (int i = 0; i < 9; i++) {
            Item it = mc.player.getInventory().getStack(i).getItem();
            if (it == item) {
                slot = i;
                break;
            }
        }
        return slot;
    }

    public int getBestAvailableToolSlot(BlockPos p) {
        BlockState blockState = mc.world.getBlockState(p);
        int bestSlot = mc.player.getInventory().selectedSlot;
        double highestSpeed = 0.0;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = mc.player.getInventory().getStack(i);
            float miningSpeed = itemStack.getMiningSpeedMultiplier(blockState);
            int efficiencyLevel = EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, itemStack);
            float modifiedSpeed = (float) (miningSpeed + ((efficiencyLevel > 0) ? (float) (Math.pow(efficiencyLevel, 2.0) + 1.0) : 0.0));
            if (!itemStack.isEmpty() && miningSpeed > 1.0f && modifiedSpeed > highestSpeed) {
                highestSpeed = modifiedSpeed;
                bestSlot = i;
            }
        }

        return bestSlot;
    }

}
