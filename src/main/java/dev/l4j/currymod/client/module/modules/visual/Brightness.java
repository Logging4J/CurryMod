package dev.l4j.currymod.client.module.modules.visual;

import dev.l4j.currymod.client.module.Module;
import net.minecraft.client.MinecraftClient;

@Module.Info(name = "Brightness", description = "Makes shit bright", category = Module.Category.VISUAL)
public class Brightness extends Module {

    @Override
    protected void onEnable() {
//        if(nullCheck()) return;
//        ((GameRendererAccessor) mc.gameRenderer).invoke$setPostProcessor(Identifier.ofVanilla("blur"));
    }

    @Override
    protected void onDisable() {
//        if (nullCheck()) return;
//        mc.gameRenderer.clearPostProcessor();
//        mc.gameRenderer.togglePostProcessorEnabled();
    }
}
