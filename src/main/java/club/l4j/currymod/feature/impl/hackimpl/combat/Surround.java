package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.util.Center;
import club.l4j.currymod.util.WorldUtils;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

@Hack.Construct(name = "Surround", description = "HvH Moment", category = Hack.Category.COMBAT)
public class Surround extends Hack {

    OptionMode mode = new OptionMode("Mode", "Obsidian", "Obsidian", "Bedrock");
    OptionBoolean center = new OptionBoolean("Center", true);

    public Surround() {
        addOptions(mode, center);
    }

    int obiSlot;
    int oldSlot;

    @Override
    public void onEnable() {
        if (mc.player.isOnGround()) {
            Center.centerPlayer();
        }
    }

    @DemoListen
    public void onTick(TickEvent tickEvent) {
        if (mc.player == null) return;
        oldSlot = mc.player.getInventory().selectedSlot;
        obiSlot = -1;
        if (!mc.player.isOnGround()) {
            onDisable(mc.player.getWorld() != null ? true : false);
            return;
        }
        for (int i = 0; i <9; i++) {
            if (mc.player.getInventory().getStack(i).getItem().equals(Items.OBSIDIAN)) {
                ArrayList<BlockPos> positions = new ArrayList<>();

                positions.add(mc.player.getBlockPos().north());
                positions.add(mc.player.getBlockPos().south());
                positions.add(mc.player.getBlockPos().west());
                positions.add(mc.player.getBlockPos().east());

                for (BlockPos pos : positions) {
                    WorldUtils.placeBlock(pos, i, 1, false, false, true);
                }
            }
        }
    }
}
