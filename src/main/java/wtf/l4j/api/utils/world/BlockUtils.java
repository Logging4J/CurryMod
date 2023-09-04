package wtf.l4j.api.utils.world;

import com.google.common.collect.Sets;

import lombok.experimental.UtilityClass;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.chunk.WorldChunk;
import wtf.l4j.api.utils.IGlobals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class BlockUtils implements IGlobals {

    public final Set<Block> ORE_BLOCKS = Sets.newHashSet(
            Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.LAPIS_ORE, Blocks.DIAMOND_ORE,
            Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.COPPER_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.DEEPSLATE_COPPER_ORE, Blocks.NETHER_QUARTZ_ORE, Blocks.ANCIENT_DEBRIS,
            Blocks.NETHER_GOLD_ORE, Blocks.RAW_IRON_BLOCK, Blocks.RAW_COPPER_BLOCK,
            Blocks.RAW_GOLD_BLOCK
    );

    public static List<BlockEntity> getBlockEntities() {
        return getLoadedChunks().stream()
                .flatMap(chunk -> chunk.getBlockEntities().values().stream())
                .collect(Collectors.toList());
    }


    public List<WorldChunk> getLoadedChunks() {
        List<WorldChunk> chunks = new ArrayList<>();
        int viewDist = mc.options.getViewDistance().getValue();
        int playerChunkX = (int) mc.player.getX() / 16;
        int playerChunkZ = (int) mc.player.getZ() / 16;
        for (int x = -viewDist; x <= viewDist; x++) {
            for (int z = -viewDist; z <= viewDist; z++) {
                WorldChunk chunk = mc.world.getChunkManager().getWorldChunk(playerChunkX + x, playerChunkZ + z);
                if (chunk != null) chunks.add(chunk);
            }
        }
        return chunks;
    }

}
