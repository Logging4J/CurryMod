package dev.logging4j.currymod.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.command.Command;
import dev.logging4j.currymod.module.option.Option;
import dev.logging4j.currymod.module.option.options.OptionBoolean;
import dev.logging4j.currymod.module.option.options.OptionKeybind;
import dev.logging4j.currymod.module.option.options.OptionMode;
import dev.logging4j.currymod.module.option.options.OptionNumber;
import dev.logging4j.currymod.util.ChatUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import dev.logging4j.currymod.module.Module;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Command.Info(name = "module", usage = "module <module> <option> <value>")
public class ModuleCommand extends Command {

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal(this.getName())
                .executes(c -> sendUsageInstructions())
                .then(ClientCommandManager.argument("module", StringArgumentType.string())
                        .suggests(suggestModules())
                        .then(ClientCommandManager.argument("option", StringArgumentType.string())
                                .suggests((ctx, builder) -> {
                                    Module module = getModule(ctx);
                                    if (module != null) return suggestOptions(module).getSuggestions(ctx, builder);
                                    return CompletableFuture.completedFuture(builder.build());
                                })
                                .then(ClientCommandManager.argument("value", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            Module module = getModule(ctx);
                                            Option<?> option = getOption(ctx, module);
                                            if (module != null && option != null) {
                                                return suggestValues(option).getSuggestions(ctx, builder);
                                            }
                                            return CompletableFuture.completedFuture(builder.build());
                                        })
                                        .executes(this::executeSetOption)
                                )
                        )
                )
        );
    }

    private int executeSetOption(CommandContext<FabricClientCommandSource> ctx) {
        String moduleName = StringArgumentType.getString(ctx, "module");
        String optionName = StringArgumentType.getString(ctx, "option");
        String rawValue = StringArgumentType.getString(ctx, "value");

        Module module = CurryMod.getModuleManager().getModule(moduleName);
        if (module == null) {
            ChatUtils.sendErrorMessage("Module not found: " + moduleName);
            return 0;
        }

        Option<?> option = module.getOptions().stream()
                .filter(o -> o.getName().equalsIgnoreCase(optionName))
                .findFirst().orElse(null);

        if (option == null) {
            ChatUtils.sendErrorMessage("Option not found: " + optionName);
            return 0;
        }

        try {
            if (option instanceof OptionBoolean boolOpt) {
                if (rawValue.equalsIgnoreCase("toggle")) {
                    boolOpt.toggle();
                } else {
                    boolOpt.setValue(Boolean.parseBoolean(rawValue));
                }
            } else if (option instanceof OptionNumber numOpt) {
                if (numOpt.getValue() instanceof Integer) {
                    numOpt.setValue(Integer.parseInt(rawValue));
                } else if (numOpt.getValue() instanceof Float) {
                    numOpt.setValue(Float.parseFloat(rawValue));
                } else if (numOpt.getValue() instanceof Double) {
                    numOpt.setValue(Double.parseDouble(rawValue));
                }
            } else if (option instanceof OptionMode modeOpt) {
                if (modeOpt.getModes().contains(rawValue)) {
                    modeOpt.setValue(rawValue);
                } else {
                    ChatUtils.sendErrorMessage("Invalid mode: " + rawValue);
                }
            } else {
                ChatUtils.sendErrorMessage("Unsupported option type.");
            }
            ChatUtils.sendClientMessage("Set " + moduleName + "." + optionName + " to " + option.getValue());
        } catch (Exception e) {
            ChatUtils.sendErrorMessage("Failed to set option: " + e.getMessage());
        }

        return 1;
    }

    private SuggestionProvider<FabricClientCommandSource> suggestModules() {
        return (context, builder) -> {
            String input = builder.getRemaining().toLowerCase();
            CurryMod.getModuleManager().getModules().stream()
                    .map(Module::getName)
                    .filter(name -> name.toLowerCase().startsWith(input))
                    .forEach(builder::suggest);
            return CompletableFuture.completedFuture(builder.build());
        };
    }

    private SuggestionProvider<FabricClientCommandSource> suggestOptions(Module module) {
        return (context, builder) -> {
            String input = builder.getRemaining().toLowerCase();
            module.getOptions().stream()
                    .filter(option -> !(option instanceof OptionKeybind))
                    .map(Option::getName)
                    .filter(name -> name.toLowerCase().startsWith(input))
                    .forEach(builder::suggest);
            return CompletableFuture.completedFuture(builder.build());
        };
    }

    private SuggestionProvider<FabricClientCommandSource> suggestValues(Option<?> option) {
        return (context, builder) -> {
            String input = builder.getRemaining().toLowerCase();
            if (option instanceof OptionBoolean) {
                builder.suggest("true");
                builder.suggest("false");
            } else if (option instanceof OptionMode modeOpt) {
                modeOpt.getModes().stream()
                        .filter(mode -> mode.toLowerCase().startsWith(input))
                        .forEach(builder::suggest);
            }
            return CompletableFuture.completedFuture(builder.build());
        };
    }

    private Module getModule(CommandContext<FabricClientCommandSource> ctx) {
        try {
            String moduleName = StringArgumentType.getString(ctx, "module");
            return CurryMod.getModuleManager().getModule(moduleName);
        } catch (Exception e) {
            return null;
        }
    }

    private Option<?> getOption(CommandContext<FabricClientCommandSource> ctx, Module module) {
        if (module == null) return null;
        try {
            String optionName = StringArgumentType.getString(ctx, "option");
            Optional<Option<?>> option = module.getOptions().stream()
                    .filter(o -> o.getName().equalsIgnoreCase(optionName))
                    .findFirst();
            return option.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
