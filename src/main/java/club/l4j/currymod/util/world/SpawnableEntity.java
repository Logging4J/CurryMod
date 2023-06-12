package club.l4j.currymod.util.world;

import club.l4j.currymod.util.IGlobals;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class SpawnableEntity extends OtherClientPlayerEntity implements IGlobals {

    public SpawnableEntity(Entity e) {
        super(mc.world, new GameProfile(UUID.randomUUID(), mc.player.getGameProfile().getName()));
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
