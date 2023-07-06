package club.l4j.currymod.feature.impl.hackimpl.misc;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionMode;
import demo.knight.demobus.event.DemoListen;
import org.lwjgl.glfw.GLFW;

@Hack.Construct(name = "MiddleClick(WIP)", description = "middle click", category = Hack.Category.MISC)
public class MiddleClick extends Hack {

    public OptionMode  mode = new OptionMode("Mode", "Friend", "Pearl", "EXP");
    private boolean held = false;

    public MiddleClick(){
        addOptions(mode);
    }

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck()) return;
        if(GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == GLFW.GLFW_PRESS && !held) {
            held = true;
        }else {
            held = false;
        }
    }

}