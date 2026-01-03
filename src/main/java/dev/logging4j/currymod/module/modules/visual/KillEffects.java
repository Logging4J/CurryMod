package dev.logging4j.currymod.module.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.listener.IPacketReceiveListener;
import dev.logging4j.currymod.listener.IRender2DListener;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.module.option.options.OptionMode;
import dev.logging4j.currymod.util.RenderUtils;
import dev.logging4j.currymod.util.ResourceBank;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.Resource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Module.Info(name = "KillEffects", description = "Kill effects", category = Module.Category.VISUAL)
public class KillEffects extends Module implements IPacketReceiveListener, IRender2DListener {

    private final OptionMode mode = new OptionMode("Mode", "PhonkEdit", "Lighting", "PhonkEdit");
    public static final SoundEvent[] PHONK_SOUNDS = new SoundEvent[10];
    private static final List<Identifier> SKULLS = new ArrayList<>();

    private static Identifier currentSkull = null;
    private static long skullEndTime = 0L;
    private static boolean shaking = false;
    private static long shakeStartNanos = -1L;


    public KillEffects() {
        addOptions(mode);

        for (int i = 1; i <= 10; i++) {
            Identifier id = ResourceBank.of("phonk/" + i);
            SoundEvent event = SoundEvent.of(id);

            PHONK_SOUNDS[i - 1] = event;
            Registry.register(Registries.SOUND_EVENT, id, event);
        }

        mc.execute(() -> {
            Map<Identifier, Resource> imgs = mc.getResourceManager().findResources(
                    "textures/skulls",
                    id -> id.getNamespace().equals(CurryMod.MOD_ID)
                            && id.getPath().endsWith(".png")
            );

            List<Identifier> ids = new ArrayList<>(imgs.keySet());
            ids.sort(Comparator.comparing(Identifier::toString));

            SKULLS.clear();
            SKULLS.addAll(ids);
        });
    }

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(PacketReceiveEvent.ID, this);
        DietrichEvents2.global().subscribe(Render2DEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(PacketReceiveEvent.ID, this);
        DietrichEvents2.global().unsubscribe(Render2DEvent.ID, this);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof EntityStatusS2CPacket packet && packet.getStatus() == 3) {
            if (!(packet.getEntity(mc.world) instanceof PlayerEntity player)) return;

            switch (mode.getValue()) {
                case "Lighting" ->{
                    LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, mc.world);
                    lightningEntity.updatePositionAndAngles(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
                    mc.world.addEntity(lightningEntity);
                }

                case "PhonkEdit" -> {
                    int index = mc.world.random.nextInt(PHONK_SOUNDS.length);
                    SoundEvent sound = PHONK_SOUNDS[index];

                    if (sound == null) return;

                    currentSkull = SKULLS.get(mc.world.random.nextInt(SKULLS.size()));
                    skullEndTime = System.currentTimeMillis() + 3000;
                    mc.player.playSound(sound, 1.0f, 1.0f);
                }
            }
        }
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        if(!mode.getValue().equals("PhonkEdit")) return;
        if (currentSkull == null) return;

        long now = System.currentTimeMillis();
        long remaining = skullEndTime - now;

        if (remaining <= 0) {
            currentSkull = null;
            return;
        }

        int fullW = event.getContext().getScaledWindowWidth();
        int fullH = event.getContext().getScaledWindowHeight();

        RenderUtils.GRAYSCALE_SHADER.setUniformValue("Intensity", 1.0f);
        RenderUtils.GRAYSCALE_SHADER.render(event.getRenderTickCounter().getTickDelta(false));

        int texW = 512, texH = 512;
        double scale  = Math.min((double) fullW / texW, (double) fullH / texH) * 0.3;
        int centerX   = fullW >> 1;
        int centerY   = fullH >> 1;
        double yShift = fullH * 0.25;

        double shakeX = 0.0, shakeY = 0.0;
        if (shaking) {
            double elapsed = (System.nanoTime() - shakeStartNanos) / 1_000_000_000.0;
            if (elapsed < 1.0) {
                double decay = (1.0 - elapsed); decay *= decay;
                double phase = elapsed * 15.0 * 2 * Math.PI;
                shakeX = Math.sin(phase) * 60.0 * decay;
                shakeY = Math.sin(phase + Math.PI / 2.0) * 6.0 * decay;
            } else {
                shaking = false;
            }
        }

        double offsetX = centerX - (texW * scale) / 2.0 + shakeX;
        double offsetY = centerY - (texH * scale) / 2.0 + yShift + shakeY;

        event.getContext().getMatrices().push();
        event.getContext().getMatrices().translate(offsetX, offsetY, 0);
        event.getContext().getMatrices().scale((float) scale, (float) scale, 1f);

        event.getContext().drawTexture(currentSkull, 0, 0, 0, 0, texW, texH, texW, texH);

        event.getContext().getMatrices().pop();
    }

    @Override
    public String getDisplayInfo() {
        return mode.getValue();
    }
}
