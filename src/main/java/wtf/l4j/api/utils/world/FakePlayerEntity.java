package wtf.l4j.api.utils.world;

import net.minecraft.client.network.OtherClientPlayerEntity;
import wtf.l4j.api.utils.MinecraftInterface;

import java.util.UUID;

public class FakePlayerEntity extends OtherClientPlayerEntity implements MinecraftInterface {

    public FakePlayerEntity() {
        super(mc.world, mc.player.getGameProfile());
        copyFrom(mc.player);
        getPlayerListEntry();
        dataTracker.set(PLAYER_MODEL_PARTS, mc.player.getDataTracker().get(PLAYER_MODEL_PARTS));
        setUuid(UUID.randomUUID());
    }

    public void add() {
        unsetRemoved();
        mc.world.addEntity(this.getId(), this);
    }

    public void remove() {
        mc.world.removeEntity(this.getId(), RemovalReason.DISCARDED);
    }

}
