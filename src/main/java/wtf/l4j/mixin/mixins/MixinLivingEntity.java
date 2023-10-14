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
