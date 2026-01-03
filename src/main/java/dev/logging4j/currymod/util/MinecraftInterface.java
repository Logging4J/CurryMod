package dev.logging4j.currymod.util;

import net.minecraft.client.MinecraftClient;

public interface MinecraftInterface {
    MinecraftClient mc = MinecraftClient.getInstance();

    default boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }
}
