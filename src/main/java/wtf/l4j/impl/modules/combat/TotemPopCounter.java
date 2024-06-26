package wtf.l4j.impl.modules.combat;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import wtf.l4j.api.event.DeathListener;
import wtf.l4j.api.event.GameTickListener;
import wtf.l4j.api.event.PacketListener;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;
import wtf.l4j.api.module.option.options.OptionMode;
import wtf.l4j.api.utils.text.ChatHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static wtf.l4j.api.utils.text.TextUtil.*;

@ModuleInfo(name = "TotemPopCounter", desc = "Counts the amount of totem pops a player has had in chat", category = Category.COMBAT)
public class TotemPopCounter extends Module implements PacketListener, DeathListener, GameTickListener {

    public OptionMode mode = new OptionMode("Mode", "Gay", "Gay", "Warning", "Alert");

    public TotemPopCounter() {
        addOptions(mode);
    }
    private final Map<String, Integer> mappings = new HashMap<>();

    @Override
    public void onEnable() {
        DietrichEvents2.global().subscribe(PacketListener.PacketEvent.ID, this);
        DietrichEvents2.global().subscribe(DeathListener.LivingDeathEvent.ID, this);
        DietrichEvents2.global().subscribe(GameTickListener.GameTickEvent.ID, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketListener.PacketEvent.ID, this);
        DietrichEvents2.global().unsubscribe(DeathListener.LivingDeathEvent.ID, this);
        DietrichEvents2.global().unsubscribe(GameTickListener.GameTickEvent.ID, this);
        super.onDisable();
    }


    @Override
    public void onPacket(PacketListener.PacketEvent packetEvent) {
        if(!nullCheck() && packetEvent.getPacket() instanceof EntityStatusS2CPacket packet) {
            if(packet.getStatus() == 35) {
                Entity entity = packet.getEntity(mc.world);
                if(!(entity instanceof PlayerEntity) || entity == mc.player) return;

                int amount = mappings.getOrDefault(entity.getName().getString(), 0) + 1;
                mappings.put(entity.getName().getString(), amount);

                ChatHelper.basicMessage(GRAY + "[" + YELLOW + mode.getMode() + GRAY + "] " + WHITE + entity.getName().getString() + " has popped %s totems".formatted(amount));
            }
        }
    }

    @Override
    public void onDeath(DeathListener.LivingDeathEvent deathEvent) {
        if(mappings.containsKey(deathEvent.getEntity().getName().getString())) mappings.put(deathEvent.getEntity().getName().getString(), 0);
    }

    @Override
    public void onGameTick() {
        assert mc.player != null;
        if(mc.player.age % 10 != 0) return;

        mappings.keySet().forEach(entity -> {
            assert mc.world != null;
            Optional<AbstractClientPlayerEntity> optionalPlayerEntity = mc.world.getPlayers().stream().filter(playerEntity -> playerEntity.getName().getString().equals(entity)).findFirst();
            if(optionalPlayerEntity.isPresent()) {
                PlayerEntity player = optionalPlayerEntity.get();
                if(player.isDead() || player.getHealth() <= 0) mappings.put(entity, 0);
            }
        });
    }
}