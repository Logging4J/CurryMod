package club.l4j.currymod.core.util.world;

import club.l4j.currymod.core.util.IGlobals;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class SpawnableEntity extends OtherClientPlayerEntity implements IGlobals {

    public SpawnableEntity(Entity e, String name) {
        super(mc.world, new GameProfile(UUID.randomUUID(), name));
        copyPositionAndRotation(e);
        setPose(e.getPose());
    }

    public void spawn(){
        unsetRemoved();
        mc.world.addEntity(getId(), this);
    }

    public void destroy(){
        mc.world.removeEntity(getId(), RemovalReason.DISCARDED);
    }
}
