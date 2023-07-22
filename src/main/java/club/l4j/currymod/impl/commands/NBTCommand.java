package club.l4j.currymod.impl.commands;

import club.l4j.currymod.core.command.Command;
import net.minecraft.nbt.NbtCompound;

@Command.Construct(name = "NBT", description = "view nbt data", alias = {"nbt"}, usage = "nbt")
public class NBTCommand extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!mc.player.getInventory().getStack(mc.player.getInventory().selectedSlot).isEmpty()){
            NbtCompound compound = mc.player.getInventory().getStack(mc.player.getInventory().selectedSlot).getNbt();
            String data = compound == null ? "No NBTData" : compound.asString();
            sendMsg("NBT:  " + data);
        }else {
            sendMsg("Hold An Item");
            return;
        }
        super.onTrigger(arguments);
    }
}
