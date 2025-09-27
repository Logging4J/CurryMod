package dev.l4j.currymod.client.hud;

import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.util.MinecraftInterface;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class HudElement implements MinecraftInterface {

    @Getter private String name, description;
    public int x, y, width, height;
    public float scale;
    @Getter @Setter private boolean shown;

    public HudElement() {
        this.name = this.getConstructor().name();
        this.description = this.getConstructor().description();
        this.x = this.getConstructor().defaultX();
        this.y = this.getConstructor().defaultY();
        this.shown = true;
        this.scale = 1.0f;
    }

    public void toggle() {
        this.setShown(!shown);
    }

    public void clampHudElement() {
        int scaledWidth = (int) (width * scale);
        int scaledHeight = (int) (height * scale);

        int maxX = mc.getWindow().getScaledWidth() - scaledWidth;
        int maxY = mc.getWindow().getScaledHeight() - scaledHeight;

        x = Math.max(0, Math.min(x, (int)(maxX / scale)));
        y = Math.max(0, Math.min(y, (int)(maxY / scale)));
    }

    public abstract void render(DrawContext context);

    public Info getConstructor() {
        return this.getClass().getAnnotation(Info.class);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String name();
        String description();
        int defaultX();
        int defaultY();
    }

}
