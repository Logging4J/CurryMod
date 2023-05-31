package club.l4j.currymod.graphics.spoofgui;

import club.l4j.currymod.util.TextUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class CheatSheetScreen extends Screen {
    public CheatSheetScreen() {
        super(Text.of("cheat"));
    }

    public ButtonWidget back;

    @Override
    protected void init() {
        back = ButtonWidget.builder(Text.of("Back"), (button) -> {
            client.setScreen(new SpoofScreen());
        }).position(width/2 - 80, height - 25).build();

        addDrawableChild(back);
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        //Lunar
        client.textRenderer.drawWithShadow(matrices, Text.of("Lunar: "),1,1,-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Channel: REGISTER"),4,1 + client.textRenderer.fontHeight,-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Data: Lunar-Client"),4,1 + (client.textRenderer.fontHeight * 2),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Bytes: "+ TextUtil.GREEN +"True"),4,1 + (client.textRenderer.fontHeight * 3),-1);

        //Forge
        client.textRenderer.drawWithShadow(matrices, Text.of("Forge: "),1,1 + (client.textRenderer.fontHeight * 5),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Data: FML"),4,1 + (client.textRenderer.fontHeight * 6),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Bytes:" + TextUtil.RED + " False"),4,1 + (client.textRenderer.fontHeight * 7),-1);

        //LabyMod
        client.textRenderer.drawWithShadow(matrices, Text.of("LabyMod: "),1,1 + (client.textRenderer.fontHeight * 9),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Data: LMC"),4,1 + (client.textRenderer.fontHeight * 10),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Bytes:" + TextUtil.RED + " False"),4,1 + (client.textRenderer.fontHeight * 11),-1);

        //PvPLounge
        client.textRenderer.drawWithShadow(matrices, Text.of("PvPLounge: "),1,1 + (client.textRenderer.fontHeight * 13),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Data: PLC18"),4,1 + (client.textRenderer.fontHeight * 14),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Bytes:" + TextUtil.GREEN + " True"),4,1 + (client.textRenderer.fontHeight * 15),-1);

        //CheatBreaker
        client.textRenderer.drawWithShadow(matrices, Text.of("CheatBreaker: "),1,1 + (client.textRenderer.fontHeight * 17),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Data: CB"),4,1 + (client.textRenderer.fontHeight * 18),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Bytes:" + TextUtil.RED + " False"),4,1 + (client.textRenderer.fontHeight * 19),-1);

        //Geyser
        client.textRenderer.drawWithShadow(matrices, Text.of("Geyser: "),1,1 + (client.textRenderer.fontHeight * 21),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Data: Geyser"),4,1 + (client.textRenderer.fontHeight * 22),-1);
        client.textRenderer.drawWithShadow(matrices, Text.of("Bytes:" + TextUtil.GREEN + " True"),4,1 + (client.textRenderer.fontHeight * 23),-1);

        super.render(matrices, mouseX, mouseY, delta);
    }
}
