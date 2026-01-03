package dev.logging4j.currymod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.logging4j.currymod.command.commands.BindCommand;
import dev.logging4j.currymod.command.commands.ModuleCommand;
import dev.logging4j.currymod.command.commands.PrefixCommand;
import dev.logging4j.currymod.command.commands.ToggleCommand;
import dev.logging4j.currymod.listener.IMessageSendListener;
import dev.logging4j.currymod.util.ChatUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager implements IMessageSendListener {
    public static final CommandDispatcher<FabricClientCommandSource> DISPATCHER = new CommandDispatcher<>();
    public static final FabricClientCommandSource COMMAND_SOURCE = null;
    private final List<Command> commands;

    @Getter
    @Setter
    private String prefix = "-";

    public CommandManager() {
        commands = new CopyOnWriteArrayList<>();

        add(PrefixCommand.class);
        add(ToggleCommand.class);
        add(BindCommand.class);
        add(ModuleCommand.class);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            commands.forEach(command -> {
                command.register(DISPATCHER);
            });
        });

        DietrichEvents2.global().subscribe(MessageSendEvent.ID, this);
    }

    @SneakyThrows
    private void add(Class<? extends Command> commandClass) {
        commands.add(commandClass.getConstructor().newInstance());
    }

    @Override
    public void onSendMessage(MessageSendEvent event) {
        if(event.getMessage().startsWith(prefix)) {
            try {
                CommandManager.DISPATCHER.execute(CommandManager.DISPATCHER.parse(event.getMessage().substring(prefix.length()), CommandManager.COMMAND_SOURCE));
            } catch (CommandSyntaxException e) {
                ChatUtils.sendErrorMessage("Not a valid command.");
            } finally {
                event.cancel();
            }
        }
    }
}
