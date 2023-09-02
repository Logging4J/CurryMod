package wtf.l4j.impl.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.block.entity.*;
import net.minecraft.util.math.Box;

import wtf.l4j.api.event.WorldRenderListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.utils.BlockUtils;
import wtf.l4j.api.utils.render.RenderUtils;

import java.awt.*;

@ModuleInfo(name = "StorageESP", desc = "Highlight storage blocks", category = Category.VISUAL)
public class StorageESP extends Module implements WorldRenderListener {

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
            if(blockEntity instanceof ChestBlockEntity || blockEntity instanceof BarrelBlockEntity){
                RenderUtils.draw3DBox(event.getStack(), box, new Color(55, 255, 0, 255), 0.2f);
            } else if (blockEntity instanceof EnderChestBlockEntity) {
                RenderUtils.draw3DBox(event.getStack(), box, new Color(123, 0, 255, 255), 0.2f);
            }else if (blockEntity instanceof ShulkerBoxBlockEntity){
                RenderUtils.draw3DBox(event.getStack(), box, new Color(0, 225, 255, 255), 0.2f);
            }
        }
    }
}
