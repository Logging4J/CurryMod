package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.feature.options.impl.OptionMode;
import club.l4j.currymod.mixin.minecraft.IMinecraftClient;
import club.l4j.currymod.util.WorldUtils;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.ThrowablePotionItem;

@Hack.Construct(name = "FastUse", description = "Use Items quickly", category = Hack.Category.MISC)
public class FastUse extends Hack {

    OptionBoolean blocks = new OptionBoolean("Blocks", true);
    OptionBoolean throwable = new OptionBoolean("Throwable", true);

    public FastUse(){
        addOptions(blocks, throwable);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck()){return;}
        if(mc.player.getInventory().getMainHandStack().getItem() instanceof BlockItem && blocks.isEnabled()){
            ((IMinecraftClient) mc).setItemUseCooldown(0);
        }
        if(WorldUtils.THROWABLES.contains(mc.player.getInventory().getMainHandStack().getItem()) && throwable.isEnabled()){
            ((IMinecraftClient) mc).setItemUseCooldown(0);
        }
    }
}
