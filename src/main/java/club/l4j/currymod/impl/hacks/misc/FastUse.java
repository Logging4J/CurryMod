package club.l4j.currymod.impl.hacks.misc;

import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionBoolean;
import club.l4j.currymod.mixin.minecraft.IMinecraftClient;
import club.l4j.currymod.core.util.player.PlayerUtil;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.item.BlockItem;

@Hack.Construct(name = "FastUse", description = "Use Items quickly", category = Hack.Category.MISC)
public class FastUse extends Hack {

    public OptionBoolean blocks = new OptionBoolean("Blocks", true);
    public OptionBoolean throwable = new OptionBoolean("Throwable", true);

    public FastUse(){
        addOptions(blocks, throwable);
    }

    @DemoListen
    public void onTick(TickEvent e) {
        if(nullCheck()) return;
        if(mc.player.getInventory().getMainHandStack().getItem() instanceof BlockItem && blocks.isEnabled()){
            ((IMinecraftClient) mc).setItemUseCooldown(0);
        }
        if(PlayerUtil.THROWABLES.contains(mc.player.getInventory().getMainHandStack().getItem()) && throwable.isEnabled()){
            ((IMinecraftClient) mc).setItemUseCooldown(0);
        }
    }
}
