package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import club.l4j.currymod.util.world.SpawnableEntity;

@Command.Construct(name = "FakePlayer", description = "Fake player", alias = {"fakeplayer"})
public class FakePlayer extends Command {

    @Override
    public void onTrigger(String arguments) {
        SpawnableEntity e = new SpawnableEntity(mc.player);
        e.spawn();
        super.onTrigger(arguments);
    }
}
