package club.l4j.currymod.impl.commands;

import club.l4j.currymod.core.command.Command;

@Command.Construct(name = "Prefix", description = "Help command", alias = {"prefix"}, usage = "prefix <prefix>")
public class PrefixCommand extends Command {
    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()) {
            String[] split = arguments.split(" ");
            String prefix = split[0].toLowerCase();
            setPrefix(prefix);
            sendMsg("Set Prefix to " + prefix);
        }else{
            sendMsg(getUsage());
        }
        super.onTrigger(arguments);
    }
}
