package club.l4j.currymod.impl.hacks.misc;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.core.event.events.TickEvent;
import club.l4j.currymod.core.hack.Hack;
import club.l4j.currymod.core.hack.options.impl.OptionMode;
import club.l4j.currymod.core.util.TextUtil;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

@Hack.Construct(name = "MiddleClick", description = "middle click", category = Hack.Category.MISC)
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