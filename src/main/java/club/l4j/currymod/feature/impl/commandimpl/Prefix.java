package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;

@Command.Construct(name = "Prefix", description = "Help command", alias = {"prefix"}, usage = "prefix <prefix>")
public class Prefix extends Command {
    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()) {
            String[] split = arguments.split(" ");
            String prefix = split[0].toLowerCase();
            setPrefix(prefix);
        }
        super.onTrigger(arguments);
    }
}
