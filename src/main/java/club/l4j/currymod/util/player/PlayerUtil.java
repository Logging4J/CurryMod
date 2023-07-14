package club.l4j.currymod.util.player;

import club.l4j.currymod.util.IGlobals;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerUtil implements IGlobals {

    public static Set<Item> THROWABLES = Sets.newHashSet(
            Items.SNOWBALL, Items.EXPERIENCE_BOTTLE, Items.EGG, Items.SPLASH_POTION, Items.ENDER_PEARL
    );

    public static Set<Block> ORE_BLOCKS = Sets.newHashSet(
            Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.LAPIS_ORE, Blocks.DIAMOND_ORE,
            Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.COPPER_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DEEPSLATE_COPPER_ORE, Blocks.NETHER_QUARTZ_ORE, Blocks.ANCIENT_DEBRIS
    );

    public static int getBestAvailableToolSlot(BlockPos p) {
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

    public static BlockPos getPlayerPosFloored(){
        return new BlockPos((int) Math.floor(mc.player.getX()), (int) Math.floor(mc.player.getY()), (int) Math.floor(mc.player.getZ()));
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float n, int n2, boolean b, boolean b2, int n3) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        int getX = blockPos.getX();
        int getY = blockPos.getY();
        int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }

    public static float[] getRotationToEntity(Entity target) {
        double deltaX = target.getX() - mc.player.getX();
        double deltaZ = target.getZ() - mc.player.getZ();
        double deltaY = target.getY() - 3.5 + target.getEyeHeight(target.getPose()) - mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose());
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
        float yaw = (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90);
        float pitch = (float) -Math.toDegrees(Math.atan2(deltaY, distance));
        return new float[]{yaw, pitch};
    }

}
