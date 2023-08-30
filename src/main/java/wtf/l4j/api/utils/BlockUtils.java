package wtf.l4j.api.utils;

import com.google.common.collect.Sets;

import lombok.experimental.UtilityClass;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.Set;

@UtilityClass
public class BlockUtils implements IGlobals{

    public final Set<Block> ORE_BLOCKS = Sets.newHashSet(
            Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.LAPIS_ORE, Blocks.DIAMOND_ORE,
            Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.COPPER_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DEEPSLATE_COPPER_ORE, Blocks.NETHER_QUARTZ_ORE, Blocks.ANCIENT_DEBRIS,
            Blocks.NETHER_GOLD_ORE, Blocks.RAW_IRON_BLOCK, Blocks.RAW_COPPER_BLOCK,
            Blocks.RAW_GOLD_BLOCK
    );
}
