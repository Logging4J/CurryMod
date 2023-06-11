package club.l4j.currymod.graphics.spoofgui;

import club.l4j.currymod.util.TextUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class SpoofScreen extends Screen {

    public SpoofScreen() {
        super(Text.of("spoof"));
    }

    public static boolean asBytes = false;
    public static String dataString = "";
    public static String channelString = "";

    public TextFieldWidget data;
    public TextFieldWidget channel;
    public ButtonWidget cheatSheet;
    public ButtonWidget bytes;
    public ButtonWidget done;

    @Override
    protected void init() {
        channel = new TextFieldWidget(client.textRenderer, width/2 - 90, height/2 - 25, 200, 20, Text.of("sex"));
        data = new TextFieldWidget(client.textRenderer, width/2 - 90, height/2, 200, 20, Text.of("sex"));
        cheatSheet = ButtonWidget.builder(Text.of("Cheat Sheet"), (button) -> {
            client.setScreen(new CheatSheetScreen());
        }).position(10, 4).build();

        bytes = ButtonWidget.builder(Text.of("Bytes: " + (asBytes ? TextUtil.GREEN + "True" : TextUtil.RED + "False")), (button) -> {
            asBytes = !asBytes;
            button.setMessage(Text.of("Bytes: " + (asBytes ? TextUtil.GREEN + "True" : TextUtil.RED + "False")));
        }).position(width/2 - 90, height/2 + 25).build();

        done = ButtonWidget.builder(Text.of("Done"), (button) -> {
            client.setScreen(new MultiplayerScreen(new TitleScreen()));
        }).position(width/2 - 90, height/2 + 50).build();

        addDrawableChild(done);
        addDrawableChild(bytes);
        addDrawableChild(cheatSheet);
        addDrawableChild(channel);
        addDrawableChild(data);

        super.init();
    }



    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        dataString = data.getText();
        channelString = channel.getText();
        context.drawTextWithShadow(client.textRenderer, Text.of("Channel:"), width/2 - (92 + client.textRenderer.getWidth(Text.of("Channel:"))),height/2 - 20, -1);
        context.drawTextWithShadow(client.textRenderer, Text.of("Data:"), width/2 - (92 + client.textRenderer.getWidth(Text.of("Data:"))),height/2 + 5, -1);
        super.render(context, mouseX, mouseY, delta);
    }
}
