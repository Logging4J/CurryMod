package dev.l4j.currymod.client.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.command.Command;
import dev.l4j.currymod.util.ChatUtils;
import dev.l4j.currymod.client.module.Module;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.util.Formatting;

import java.util.concurrent.CompletableFuture;

@Command.Info(name = "toggle", usage = "toggle <module>")
public class ToggleCommand extends Command {
    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal(this.getName())
                .executes(context -> sendUsageInstructions())
                .then(ClientCommandManager.argument("module", StringArgumentType.string())
                        .suggests(suggestModules())
                        .executes(this::handleCommand)
                )
        );
    }

    private int handleCommand(CommandContext<FabricClientCommandSource> context) {
        String name = StringArgumentType.getString(context, "module");
        Module module = CurryMod.INSTANCE.moduleManager.getModule(name);
        if (module == null) {
            return ChatUtils.sendClientMessage("That module doesn't exist.", 0);
        } else {
            module.toggle();
            return ChatUtils.sendClientMessage("Module " + name + Formatting.WHITE + " ["+(module.isEnabled() ? Formatting.GREEN+ "Enabled" : Formatting.RED + "Disabled") + Formatting.WHITE + "]", 1);
        }
    }

    private SuggestionProvider<FabricClientCommandSource> suggestModules() {
        return (context, builder) -> {
            String input = builder.getRemaining().toLowerCase();
            CurryMod.INSTANCE.moduleManager.getModules().stream()
                    .map(Module::getName)
                    .filter(name -> name.toLowerCase().startsWith(input))
                    .forEach(builder::suggest);
            return CompletableFuture.supplyAsync(builder::build);
        };
    }

}
