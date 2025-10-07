package dev.l4j.currymod.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementUtils implements MinecraftInterface {

    public boolean isMoving() {
        return mc.player.input.playerInput.forward()
                || mc.player.input.playerInput.backward()
                || mc.player.input.playerInput.left()
                || mc.player.input.playerInput.right();
    }
}
