package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.util.Player.MovementUtils;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

@Hack.Construct(name = "Surround", description = "HvH Moment", category = Hack.Category.COMBAT)
public class Surround extends Hack {

    OptionBoolean center = new OptionBoolean("Center", true);
    OptionBoolean autoDisable = new OptionBoolean("AutoDisable", false);
    OptionBoolean disableYChange = new OptionBoolean("DisableYChange", true);

    public Surround() {
        addOptions(center, autoDisable, disableYChange);
    }

    int obiSlot;
    int oldSlot;

    @DemoListen
    public void onTick(TickEvent e) {
        if (nullCheck()){ return;}

        oldSlot = mc.player.getInventory().selectedSlot;
        obiSlot = -1;

        if (!mc.player.isOnGround() && disableYChange.isEnabled()) {
            toggle();
            return;
        }

        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(mc.player.getBlockPos().down());
        positions.add(mc.player.getBlockPos().north());
        positions.add(mc.player.getBlockPos().east());
        positions.add(mc.player.getBlockPos().south());
        positions.add(mc.player.getBlockPos().west());

        for (int i = 0; i < 9; i++) {
            if(mc.player.getInventory().getStack(i).getItem().equals(Items.OBSIDIAN)){
                obiSlot = i;
                break;
            }
            if(mc.player.getInventory().getStack(i).getItem().equals(Items.ENDER_CHEST)){
                obiSlot = i;
                break;
            }
            if(mc.player.getInventory().getStack(i).getItem().equals(Items.CRYING_OBSIDIAN)){
                obiSlot = i;
                break;
            }
        }
        if (obiSlot == -1) {
            Command.sendMsg("No More Blocks");
            toggle();
            return;
        }
        for (BlockPos pos : positions) {
            if (!mc.world.getBlockState(pos).getMaterial().isReplaceable()) continue;
            for (Direction direction : Direction.values()) {
                if (!mc.world.getBlockState(pos.offset(direction)).getMaterial().isReplaceable()) {
                    mc.player.getInventory().selectedSlot = obiSlot;
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(pos), direction, pos, false));
                    mc.player.getInventory().selectedSlot = oldSlot;
                }
            }
        }
        if(autoDisable.isEnabled()) {
            toggle();
        }
    }

    @Override
    public void onEnable() {
        if(center.isEnabled()) {
            MovementUtils.centerPlayer();
        }
        super.onEnable();
    }
}