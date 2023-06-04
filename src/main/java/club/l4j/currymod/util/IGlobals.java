package club.l4j.currymod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public interface IGlobals {

    MinecraftClient mc = MinecraftClient.getInstance();

    TextRenderer tr = mc.textRenderer;
}
