package dev.logging4j.currymod.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.command.Command;
import dev.logging4j.currymod.util.ChatUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

@Command.Info(name = "prefix", usage = "prefix <prefix>")
public class PrefixCommand extends Command {

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal(this.getName())
                .executes(context -> sendUsageInstructions())
                .then(ClientCommandManager.argument("currymodprefix", StringArgumentType.greedyString())
                        .executes(context -> handleCommand(context, StringArgumentType.getString(context, "currymodprefix")))
                )
        );
    }

    private int handleCommand(CommandContext<FabricClientCommandSource> context, String prefix) {
        CurryMod.getCommandManager().setPrefix(prefix);
        return ChatUtils.sendClientMessage("CurryMod prefix set too \"" + prefix + "\"", 1);
    }
}
