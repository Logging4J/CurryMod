package dev.l4j.currymod.client.hud;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.hud.elements.LocationElement;
import dev.l4j.currymod.client.hud.elements.ModuleListElement;
import dev.l4j.currymod.client.hud.elements.WatermarkElement;
import dev.l4j.currymod.client.hud.elements.WelcomerElement;
import dev.l4j.currymod.client.screen.HudEditorScreen;
import dev.l4j.currymod.listener.IRender2DListener;
import dev.l4j.currymod.util.MinecraftInterface;
import lombok.Getter;
import lombok.SneakyThrows;
import org.joml.Matrix3x2fStack;

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

        DietrichEvents2.global().subscribe(Render2DEvent.ID, this);
    }

    @SneakyThrows
    private void add(Class<? extends HudElement> hudClass) {
        hudElements.add(hudClass.getConstructor().newInstance());
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        if (mc.currentScreen instanceof HudEditorScreen) return;
        hudElements.forEach(hudElement -> {
            if (hudElement.isShown()) {
                Matrix3x2fStack stack = event.getContext().getMatrices();

                stack.pushMatrix();

                stack.scale(hudElement.scale);

                hudElement.render(event.getContext());

                stack.popMatrix();
            }
        });
    }
}
