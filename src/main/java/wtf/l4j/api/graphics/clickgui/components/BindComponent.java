package wtf.l4j.api.graphics.clickgui.components;

import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import wtf.l4j.api.graphics.Constants;
import wtf.l4j.api.graphics.clickgui.ModuleButton;
import wtf.l4j.api.module.option.Option;

public class BindComponent extends Component {

    private boolean binding = false;

    public BindComponent(Option option, ModuleButton moduleButton, int yOffset) {
        super(option, moduleButton, yOffset);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(moduleButton.window.getX(), moduleButton.window.getY() + moduleButton.yOffset + yOffset, moduleButton.window.getX() + Constants.WIDTH, moduleButton.window.getY() + moduleButton.yOffset + yOffset + Constants.HEIGHT, Constants.BACKGROUND_COLOR);
        int scanCode = GLFW.glfwGetKeyScancode(moduleButton.module.getKey());
        context.drawTextWithShadow(mc.textRenderer, "Bind: " + getBindName(scanCode), moduleButton.window.getX() + 1, moduleButton.window.getY() + moduleButton.yOffset + yOffset + 1, -1);
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
                moduleButton.module.setKey(GLFW.GLFW_KEY_UNKNOWN);
            }else{
                moduleButton.module.setKey(keyCode);
            }
            binding = false;
        }
    }

    public String getBindName(int code){
        if(!binding) {
            if (code == 0) return "NONE";
            else return GLFW.glfwGetKeyName(moduleButton.module.getKey(), code);
        }else {
            return "...";
        }
    }
}
