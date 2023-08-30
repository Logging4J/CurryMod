package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.Getter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;

public interface EffectListener {

    void onEffect(final EffectEvent potionEvent);

    final class EffectEvent extends CancellableEvent<EffectListener> {

        public static final int ID = 4;

        @Getter private StatusEffect statusEffect;
        @Getter private LivingEntity entity;

        public EffectEvent(StatusEffect statusEffect, LivingEntity entity) {
            this.statusEffect = statusEffect;
            this.entity = entity;
        }

        //@formatter:off
        @Override
        public void call(EffectListener listener) {
            listener.onEffect(this);
        }
        //@formatter:on
    }

}
