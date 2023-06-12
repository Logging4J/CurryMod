package club.l4j.currymod.feature.impl.commandimpl;

import club.l4j.currymod.feature.core.Command;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Command.Construct(name = "VClip", description = "Vertically clips through blocks", alias = {"vclip"})
public class VClip extends Command {

    @Override
    public void onTrigger(String arguments) {
        if(!arguments.isEmpty()){
            String[] split = arguments.split(" ");
            int value = Integer.parseInt(split[0]);
            for(int i = 0; i < 10; i++){
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(),mc.player.getY(),mc.player.getZ(),mc.player.isOnGround()));
            }
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(),mc.player.getY() + 10, mc.player.getZ(),mc.player.isOnGround()));
            mc.player.setPos(mc.player.getX(),mc.player.getY() + value,mc.player.getZ());
        }else{
            sendMsg("Please Enter an Number Value");
        }
        super.onTrigger(arguments);
    }

}
