package dev.logging4j.currymod.hud;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.hud.elements.*;
import dev.logging4j.currymod.listener.IRender2DListener;
import dev.logging4j.currymod.screen.hud.HudEditorScreen;
import dev.logging4j.currymod.util.MinecraftInterface;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HudManager implements IRender2DListener, MinecraftInterface {

    @Getter
    private final List<HudElement> hudElements;

    public HudManager() {
        hudElements = new CopyOnWriteArrayList<>();

        add(WatermarkElement.class);
        add(ModuleListElement.class);
        add(LocationElement.class);
        add(WelcomerElement.class);
        add(TextRadarElement.class);
        add(InfoElement.class);

        DietrichEvents2.global().subscribe(Render2DEvent.ID, this);
        CurryMod.LOGGER.info("HudManager Initialized");
    }

    @SneakyThrows
    private void add(Class<? extends HudElement> hudClass) {
        hudElements.add(hudClass.getConstructor().newInstance());
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        if (mc.currentScreen instanceof HudEditorScreen || mc.getDebugHud().shouldShowDebugHud()) return;
        hudElements.forEach(hudElement -> {
            hudElement.clampHudElement();
            if (hudElement.isShown()) {
                MatrixStack stack = event.getContext().getMatrices();

                stack.push();

                stack.scale(hudElement.scale, hudElement.scale, hudElement.scale);

                hudElement.render(event.getContext());

                stack.pop();
            }
        });
    }
}
