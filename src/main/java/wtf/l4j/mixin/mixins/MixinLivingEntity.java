package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import wtf.l4j.api.event.DeathListener;
import wtf.l4j.api.event.EffectListener;
import wtf.l4j.mixin.invokers.EntityInvoker;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Unique private final LivingEntity entity = ((LivingEntity) (Object) this);
    @Unique private int bounceTimer;

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"))
    public void travel(LivingEntity instance, int i, boolean b){}

    @Redirect(method = "tickFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"))
    public void tickFallFlying(LivingEntity instance, int i, boolean b){
        if(instance.getVelocity().y == 0){
            if(bounceTimer > 1) ((EntityInvoker) instance).callSetFlag(7, b);
            bounceTimer += 1;
        }else {
            bounceTimer = 0;
        }
    }

    @Inject(method = "hasStatusEffect", at = @At("RETURN"), cancellable = true)
    public void hasStatusEffect(StatusEffect effect, CallbackInfoReturnable<Boolean> cir) {
        EffectListener.EffectEvent effectEvent = new EffectListener.EffectEvent(effect, entity);
        //@formatter:off
        DietrichEvents2.global().postInternal(EffectListener.EffectEvent.ID, effectEvent);
        //@formatter:on
        if (effectEvent.isCancelled()) {
            cir.setReturnValue(false);
        }
    }
    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onDeath(DamageSource damageSource, CallbackInfo ci) {
        DeathListener.LivingDeathEvent deathEvent = new DeathListener.LivingDeathEvent(entity, damageSource);
        //@formatter:off
        DietrichEvents2.global().postInternal(DeathListener.LivingDeathEvent.ID, deathEvent);
        //@formatter:on
        if (deathEvent.isCancelled()) {
            ci.cancel();
        }
    }
}
