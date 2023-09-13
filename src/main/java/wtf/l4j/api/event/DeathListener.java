package wtf.l4j.api.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.Packet;

public interface DeathListener {


    void onDeath(final DeathListener.LivingDeathEvent deathEvent);


    final class LivingDeathEvent extends CancellableEvent<DeathListener> {
        public static final int ID = 7;
        @Getter @Setter private DamageSource source;
        @Getter @Setter private LivingEntity entity;

        public LivingDeathEvent(final LivingEntity entity, DamageSource source) {
            this.entity = entity;
            this.source = source;
        }

        @Override
        public void call(DeathListener listener) {
            listener.onDeath(this);
        }
    }
}
