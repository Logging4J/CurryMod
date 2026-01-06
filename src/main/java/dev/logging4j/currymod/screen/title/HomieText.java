package dev.logging4j.currymod.screen.title;


import dev.logging4j.currymod.util.MathUtils;
import dev.logging4j.currymod.util.MinecraftInterface;
import lombok.Getter;

import java.awt.*;

@Getter
public class HomieText implements MinecraftInterface {

    private static final double DEFAULT_SPEED = 0.5;

    private final String name;
    private final Color color;
    private final int screenWidth, screenHeight;
    private float x, y, velocityX, velocityY;

    public HomieText(String name, int screenWidth, int screenHeight) {
        this.name = name;
        this.x = MathUtils.random(40, screenWidth - 40);
        this.y = MathUtils.random(40, screenHeight - 40);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.color = new Color((int)(Math.random() * 0x1000000));

        this.velocityX = generateInitialSpeed();
        this.velocityY = generateInitialSpeed();
    }

    public void updatePos() {
        if (this.x + mc.textRenderer.getWidth(name) >= screenWidth || this.x <= 0) {
            velocityX *= -1;
        }
        if (this.y + mc.textRenderer.fontHeight >= screenHeight || this.y <= 0) {
            velocityY *= -1;
        }

        this.x += velocityX;
        this.y += velocityY;
    }

    private float generateInitialSpeed() {
        return (float) ((Math.random() < 0.5 ? -1 : 1) * HomieText.DEFAULT_SPEED);
    }


}