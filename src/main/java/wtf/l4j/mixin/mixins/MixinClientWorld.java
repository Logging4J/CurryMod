package wtf.l4j.mixin.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wtf.l4j.CurryMod;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.ChatHelper;
import wtf.l4j.impl.modules.misc.VisualRange;

import static wtf.l4j.api.utils.text.TextUtil.*;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "addEntity", at = @At("HEAD"))
    private void addEntity(int id, Entity entity, CallbackInfo ci) {
        if(CurryMod.getInstance().getManagers().getModuleManager().getModule(VisualRange.class).get().isEnabled()){
            if(entity instanceof PlayerEntity && entity != client.player){
                ChatHelper.basicMessage(GRAY + "[" + DARK_RED + "WARNING" + GRAY + "] "+ WHITE + entity.getName().getString() + " has entered your visual range");
            }
        }
    }

}
