package club.l4j.currymod.graphics.clickgui.components;

import club.l4j.currymod.graphics.Constants;
import club.l4j.currymod.graphics.clickgui.HackButton;
import club.l4j.currymod.feature.options.Option;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

public class BindComponent extends Component {

    private boolean binding = false;

    public BindComponent(Option option, HackButton hackButton, int yOffset) {
        super(option, hackButton, yOffset);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(hackButton.window.x, hackButton.window.y + hackButton.yOffset + yOffset,hackButton.window.x + Constants.WIDTH,hackButton.window.y + hackButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);

        int scanCode = GLFW.glfwGetKeyScancode(hackButton.hack.getKey());
        String bindName = GLFW.glfwGetKeyName(hackButton.hack.getKey(),scanCode);

        if(scanCode != -1) {
            context.drawTextWithShadow(mc.textRenderer, "Bind: " + (binding ? "..." : bindName), hackButton.window.x + 1, hackButton.window.y + hackButton.yOffset + yOffset + 1, -1);
        }else {
            context.drawTextWithShadow(mc.textRenderer, "Bind: NONE", hackButton.window.x + 1, hackButton.window.y + hackButton.yOffset + yOffset + 1, -1);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(hovered((int) mouseX, (int) mouseY)){
            if(button == 0){
                binding = !binding;
            }
        }
    }


    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if(binding){
            if (keyCode == GLFW.GLFW_KEY_DELETE || keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_BACKSPACE || keyCode == GLFW.GLFW_KEY_UNKNOWN) {
                hackButton.hack.setKey(GLFW.GLFW_KEY_UNKNOWN);
            }else{
                hackButton.hack.setKey(keyCode);
            }
            binding = false;
        }
    }
}
