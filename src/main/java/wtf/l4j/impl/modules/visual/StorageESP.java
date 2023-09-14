package wtf.l4j.impl.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.block.entity.*;
import net.minecraft.util.math.Box;

import wtf.l4j.api.event.WorldRenderListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionBoolean;
import wtf.l4j.api.utils.world.BlockUtils;
import wtf.l4j.api.utils.render.RenderUtils;

import java.awt.*;

@ModuleInfo(name = "StorageESP", desc = "Highlight storage blocks", category = Category.VISUAL)
public class StorageESP extends Module implements WorldRenderListener {

    public static OptionBoolean chest = new OptionBoolean("Chest", true);
    public static OptionBoolean barrel = new OptionBoolean("Barrel", true);
    public static OptionBoolean enderchest = new OptionBoolean("EnderChest", true);
    public static OptionBoolean shulkerbox = new OptionBoolean("ShulkerBox", true);
    public static OptionBoolean furnace = new OptionBoolean("Furnace", true);
    public static OptionBoolean hopper = new OptionBoolean("Hopper", true);
    public static OptionBoolean spawner = new OptionBoolean("Spawner", true);


    public StorageESP(){
        addOptions(chest, barrel, enderchest, shulkerbox, furnace, hopper, spawner);
    }

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(RenderWorldEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(RenderWorldEvent.ID, this);
        super.onDisable();
    }

    @Override
    public void onRenderWorld(RenderWorldEvent event) {
        for(BlockEntity blockEntity : BlockUtils.getBlockEntities()){
            Box box = new Box(blockEntity.getPos());
            if(blockEntity instanceof ChestBlockEntity && chest.isEnabled()){
                RenderUtils.draw3DBox(event.getStack(), box, new Color(55, 255, 0, 255), 0.2f);
            } else if (blockEntity instanceof EnderChestBlockEntity && enderchest.isEnabled()) {
                RenderUtils.draw3DBox(event.getStack(), box, new Color(123, 0, 255, 255), 0.2f);
            }else if (blockEntity instanceof ShulkerBoxBlockEntity && shulkerbox.isEnabled()){
                RenderUtils.draw3DBox(event.getStack(), box, new Color(0, 225, 255, 255), 0.2f);}
            else if (blockEntity instanceof BarrelBlockEntity && barrel.isEnabled()) {
                RenderUtils.draw3DBox(event.getStack(), box, new Color(0, 225, 255, 255), 0.2f);
            } else if (blockEntity instanceof FurnaceBlockEntity && furnace.isEnabled()) {
                RenderUtils.draw3DBox(event.getStack(), box, new Color(157, 203, 197, 255), 0.2f);
            } else if(blockEntity instanceof HopperBlockEntity && hopper.isEnabled()){
                RenderUtils.draw3DBox(event.getStack(), box, new Color(157, 203, 197, 255), 0.2f);
            } else if(blockEntity instanceof MobSpawnerBlockEntity && spawner.isEnabled()){
                RenderUtils.draw3DBox(event.getStack(), box, new Color(255, 0, 221, 255), 0.2f);
            }

        }
    }
}