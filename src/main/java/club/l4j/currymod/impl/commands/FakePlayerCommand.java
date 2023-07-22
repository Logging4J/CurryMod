package club.l4j.currymod.impl.commands;

import club.l4j.currymod.core.command.Command;
import club.l4j.currymod.core.util.world.SpawnableEntity;

@Command.Construct(name = "FakePlayer", description = "Fake player", alias = {"fakeplayer"}, usage = "fakeplayer")
public class FakePlayerCommand extends Command {

    @Override
    public void onTrigger(String arguments) {
        SpawnableEntity e = new SpawnableEntity(mc.player, "Pakistan");
        e.spawn();
        super.onTrigger(arguments);
    }
}
