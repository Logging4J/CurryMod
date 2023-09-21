package wtf.l4j.api.command;

import lombok.Getter;
import lombok.Setter;

import wtf.l4j.api.utils.MinecraftInterface;
import wtf.l4j.api.utils.text.ChatHelper;

public abstract class Command extends ChatHelper implements MinecraftInterface {

    private final CommandInfo command = getClass().getAnnotation(CommandInfo.class);
    @Getter @Setter private String alias = command.alais();
    @Getter @Setter private String name = command.name();
    @Getter @Setter private String desc = command.desc();

    public abstract void execute(String arguments);

}
