package dev.l4j.currymod.client.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.l4j.currymod.CurryMod;
import dev.l4j.currymod.client.cape.CapeManager;
import dev.l4j.currymod.client.command.Command;
import dev.l4j.currymod.util.ChatUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Command.Info(name = "cape", usage = "cape <enable|disable|set <capeName>>")
public class CapeCommand extends Command {

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal(this.getName())
                .executes(context -> sendUsageInstructions())
                .then(ClientCommandManager.literal("enable")
                        .executes(this::enableCape))
                .then(ClientCommandManager.literal("disable")
                        .executes(this::disableCape))
                .then(ClientCommandManager.literal("set")
                        .then(ClientCommandManager.argument("capeName", StringArgumentType.greedyString())
                                .suggests(this::suggestCapes)
                                .executes(ctx -> setCape(ctx, StringArgumentType.getString(ctx, "capeName")))))
        );
    }

    private CompletableFuture<Suggestions> suggestCapes(CommandContext<FabricClientCommandSource> context, SuggestionsBuilder builder) {
        String input = builder.getRemaining().toLowerCase();
        CurryMod.INSTANCE.capeManager.getCapes().stream()
                .map(CapeManager.Cape::name)
                .filter(name -> name.toLowerCase().startsWith(input))
                .forEach(builder::suggest);
        return CompletableFuture.completedFuture(builder.build());
    }

    private int enableCape(CommandContext<FabricClientCommandSource> context){
        CurryMod.INSTANCE.capeManager.customCape = true;
        return ChatUtils.sendClientMessage("Cape enabled.", 1);
    }

    private int disableCape(CommandContext<FabricClientCommandSource> context) {
        CurryMod.INSTANCE.capeManager.customCape = false;
        return ChatUtils.sendClientMessage("Cape disabled.", 1);
    }

    private  int setCape(CommandContext<FabricClientCommandSource> context, String capeName) {
        Optional<CapeManager.Cape> cape = CurryMod.INSTANCE.capeManager.getCapeByName(capeName);

        if (cape.isPresent()) {
            CurryMod.INSTANCE.capeManager.currentCape = cape.get();
            return ChatUtils.sendClientMessage("Cape set to: " + capeName, 1);
        } else {
            String available = CurryMod.INSTANCE.capeManager.getCapes().stream()
                    .map(CapeManager.Cape::name)
                    .collect(Collectors.joining(", "));

            return ChatUtils.sendErrorMessage("Invalid cape: " + capeName + ". Available: " + available, 0);
        }
    }
}
