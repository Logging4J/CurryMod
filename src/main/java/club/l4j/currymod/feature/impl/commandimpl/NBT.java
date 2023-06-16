package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import net.minecraft.nbt.NbtCompound;

@Command.Construct(name = "NBT", description = "view nbt data", alias = {"nbt"}, usage = "nbt")
public class NBT extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!mc.player.getInventory().getStack(mc.player.getInventory().selectedSlot).isEmpty()){
            NbtCompound tag = mc.player.getInventory().getStack(mc.player.getInventory().selectedSlot).getNbt();
            String nbt = tag == null ? "No NBTData" : tag.asString();
            sendMsg("NBT:  " + nbt);
        }else {
            sendMsg("Hold An Item");
            return;
        }
        super.onTrigger(arguments);
    }
}
