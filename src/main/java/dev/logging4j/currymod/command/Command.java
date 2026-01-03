package dev.logging4j.currymod.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.util.ChatUtils;
import dev.logging4j.currymod.util.MinecraftInterface;
import lombok.Getter;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class Command implements MinecraftInterface {

    private final String usage = this.getConstructor().usage();

    @Getter
    private final String name = this.getConstructor().name();

    public abstract void register(CommandDispatcher<FabricClientCommandSource> dispatcher);

    public Info getConstructor() {
        return this.getClass().getAnnotation(Info.class);
    }

    public int sendUsageInstructions() {
        return ChatUtils.sendErrorMessage("Usage: " + CurryMod.getCommandManager().getPrefix() + usage, 0);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String name();
        String usage();
    }
}