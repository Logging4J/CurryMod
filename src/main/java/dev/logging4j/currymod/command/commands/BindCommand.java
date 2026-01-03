package dev.logging4j.currymod.command.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.command.Command;
import dev.logging4j.currymod.module.Module;
import dev.logging4j.currymod.util.ChatUtils;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Command.Info(name = "bind", usage = "bind <module> <key>")
public class BindCommand extends Command {

    public static final String[] BIND_TRANSLATION_KEYS = {
            "unknown", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11",
            "f12", "f13", "f14", "f15", "f16", "f17", "f18", "f19", "f20", "f21", "f22", "f23", "f24", "f25", "num.lock", "keypad.0",
            "keypad.1", "keypad.2", "keypad.3", "keypad.4", "keypad.5", "keypad.6", "keypad.7",
            "keypad.8", "keypad.9", "keypad.add", "keypad.decimal", "keypad.enter", "keypad.equal", "keypad.multiply", "keypad.divide",
            "keypad.subtract", "down", "left", "right", "up", "apostrophe", "backslash", "comma",
            "equal", "grave.accent", "left.bracket", "minus", "period", "right.bracket", "semicolon", "slash",
            "space", "tab", "left.alt", "left.control", "left.shift", "left.win", "right.alt", "right.control",
            "right.shift", "right.win", "enter", "escape", "backspace", "delete", "end", "home",
            "insert", "page.down", "page.up", "caps.lock", "pause", "scroll.lock", "menu", "print.screen",
            "world.1", "world.2"
    };

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal(this.getName())
                .executes(context -> sendUsageInstructions())
                .then(ClientCommandManager.argument("name", StringArgumentType.string())
                        .suggests(suggestModules())
                        .then(ClientCommandManager.argument("key", StringArgumentType.string())
                                .suggests((context, builder) -> suggestKeyboardBinds().getSuggestions(context, builder))
                                .executes(context -> {
                                    String key = StringArgumentType.getString(context, "key");
                                    return bindKeyboard(context, key);
                                })
                        )
                )
        );
    }

    private SuggestionProvider<FabricClientCommandSource> suggestModules() {
        return (context, builder) -> {
            String input = builder.getRemaining().toLowerCase();
            CurryMod.getModuleManager().getModules().stream()
                    .map(Module::getName)
                    .filter(name -> name.toLowerCase().startsWith(input))
                    .forEach(builder::suggest);
            return CompletableFuture.supplyAsync(builder::build);
        };
    }

    private SuggestionProvider<FabricClientCommandSource> suggestKeyboardBinds() {
        return (context, builder) -> {
            Arrays.stream(BIND_TRANSLATION_KEYS).forEach(builder::suggest);
            return CompletableFuture.supplyAsync(builder::build);
        };
    }

    private int bindKeyboard(CommandContext<?> context, String key) {
        String[] data = context.getInput().split(" ");
        Module module = CurryMod.getModuleManager().getModule(data[1]);
        module.getKeybind().setValue(InputUtil.fromTranslationKey("key.keyboard." + key));
        return ChatUtils.sendClientMessage(module.getName() + " Was Binded To " + Text.translatable(key).getString(), 1);
    }
}
