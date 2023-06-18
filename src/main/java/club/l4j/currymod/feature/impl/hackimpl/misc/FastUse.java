package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.mixin.minecraft.IMinecraftClient;
import club.l4j.currymod.util.player.InvUtil;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

@Hack.Construct(name = "FastUse", description = "Use Items quickly", category = Hack.Category.MISC)
public class FastUse extends Hack {

    public OptionBoolean blocks = new OptionBoolean("Blocks", true);
    public OptionBoolean throwable = new OptionBoolean("Throwable", true);

    public FastUse(){
        addOptions(blocks, throwable);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck()){return;}
        if(mc.player.getInventory().getMainHandStack().getItem() instanceof BlockItem && blocks.isEnabled()){
            ((IMinecraftClient) mc).setItemUseCooldown(0);
        }
        if(InvUtil.THROWABLES.contains(mc.player.getInventory().getMainHandStack().getItem()) && throwable.isEnabled()){
            ((IMinecraftClient) mc).setItemUseCooldown(0);
        }
    }
}
