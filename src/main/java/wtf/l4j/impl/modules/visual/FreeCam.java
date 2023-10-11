package wtf.l4j.impl.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.event.Type;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionSlider;
import wtf.l4j.api.utils.MovementUtils;
import wtf.l4j.api.utils.world.FakePlayerEntity;

@ModuleInfo(name = "FreeCam", desc = "Lets you look around freely", category = Category.VISUAL)
public class FreeCam extends Module implements PacketListener, GameTickListener {

    private OptionSlider speed = new OptionSlider("Speed", 1, 5, 1, 5);

    private FakePlayerEntity clone;
    private Entity vehicle;
    private Vec3d playerPosition;
    private Vec2f playerRotation;

    public FreeCam(){
        addOptions(speed);
    }
 
    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);

        mc.chunkCullingEnabled = false;

        clone = new FakePlayerEntity();
        playerPosition = mc.player.getPos();
        playerRotation = new Vec2f(mc.player.getYaw(), mc.player.getPitch());
        vehicle = mc.player.getVehicle();

        clone.add();

        if(vehicle != null) mc.player.getVehicle().removeAllPassengers();

        if (mc.player.isSprinting()) sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.STOP_SPRINTING));

        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketListener.PacketEvent.ID, this);
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        mc.chunkCullingEnabled = true;
        mc.player.noClip = false;

        if(clone != null) {
            clone.remove();
            mc.player.refreshPositionAndAngles(playerPosition.getX(), playerPosition.getY(), playerPosition.getZ(), playerRotation.x, playerRotation.y);
        }

        mc.player.setVelocity(Vec3d.ZERO);
        mc.player.getAbilities().flying = false;
        mc.player.getAbilities().setFlySpeed(0.05f);

        if (vehicle != null && mc.world.getEntityById(vehicle.getId()) != null) {
            mc.player.startRiding(vehicle);
        }
    }

    @Override
    public void onPacket(PacketEvent packetEvent) {
        if(packetEvent.getType() == Type.OUTGOING){
            if(packetEvent.getPacket() instanceof PlayerMoveC2SPacket || packetEvent.getPacket() instanceof ClientCommandC2SPacket){
                packetEvent.cancel();
            }
        }
    }

    @Override
    public void onGameTick() {
        mc.player.setOnGround(false);
        if(MovementUtils.isMoving()){
            mc.player.setVelocity(Vec3d.ZERO);
            mc.player.noClip = true;
        }
        mc.player.setPose(EntityPose.STANDING);
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(speed.getFloatValue() / 5.0f);

    }
}
