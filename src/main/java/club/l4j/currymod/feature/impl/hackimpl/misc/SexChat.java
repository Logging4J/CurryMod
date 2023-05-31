package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.feature.core.Hack;

@Hack.Construct(name = "SexChat", description = "sexer", category = Hack.Category.EXPLOITS)
public class SexChat extends Hack {

    @Override
    public void onEnable() {
        mc.player.networkHandler.sendChatMessage(" $$$$$$\\                      ");
        mc.player.networkHandler.sendChatMessage("$$  __$$\\                     ");
        mc.player.networkHandler.sendChatMessage("$$ /  \\__| $$$$$$\\  $$\\   $$\\ ");
        mc.player.networkHandler.sendChatMessage("\\$$$$$$\\  $$  __$$\\ \\$$\\ $$  |");
        mc.player.networkHandler.sendChatMessage(" \\____$$\\ $$$$$$$$ | \\$$$$  / ");
        mc.player.networkHandler.sendChatMessage("$$\\   $$ |$$   ____| $$  $$<  ");
        mc.player.networkHandler.sendChatMessage("\\$$$$$$  |\\$$$$$$$\\ $$  /\\$$\\ ");
        mc.player.networkHandler.sendChatMessage(" \\______/  \\_______|\\__/  \\__|");
        toggle();
        super.onEnable();
    }

}
