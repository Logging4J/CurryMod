package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionSlider;
import club.l4j.currymod.util.player.PlayerUtil;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

@Hack.Construct(name = "AntiReGear", description = "Prevents ppl from regearing", category = Hack.Category.COMBAT)
public class AntiReGear extends Hack {

    public OptionSlider range = new OptionSlider("Range", 1, 6, 1, 4);

    public AntiReGear(){
        addOptions(range);
    }

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()) return;

        List<BlockPos> targets = new ArrayList<>();
        for(BlockPos pos : PlayerUtil.getSphere(PlayerUtil.getPlayerPosFloored(), range.getFloatValue(), 0, false, true, 0)){
            if(mc.world.getBlockState(pos).getBlock() instanceof ShulkerBoxBlock){
                targets.add(pos);
            }
        }

        if(!targets.isEmpty()){
            for(BlockPos pos : targets){
                int best = PlayerUtil.getBestAvailableToolSlot(pos);
                mc.player.getInventory().selectedSlot = best;
                sendPacket(new UpdateSelectedSlotC2SPacket(best));

                Direction direction = (mc.player.getY() > pos.getY()) ? Direction.UP : Direction.DOWN;
                sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction));
                sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, pos, direction));

                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

}
