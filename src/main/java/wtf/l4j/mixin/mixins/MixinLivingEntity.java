package wtf.l4j.mixin.mixins;

import de.florianmichael.dietrichevents2.DietrichEvents2;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import wtf.l4j.api.event.EffectListener;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Unique private LivingEntity entity = ((LivingEntity) (Object) this);

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
}