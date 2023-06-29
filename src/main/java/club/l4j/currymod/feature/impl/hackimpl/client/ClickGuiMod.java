package club.l4j.currymod.feature.impl.hackimpl.client;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.graphics.clickgui.ClickGuiScreen;
import demo.knight.demobus.event.DemoListen;
import org.lwjgl.glfw.GLFW;

@Hack.Construct(name = "ClickGUI", description = "clickable GUI", category = Hack.Category.CLIENT)
public class ClickGuiMod extends Hack {


    public ClickGuiMod(){
        setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void onEnable() {
        mc.setScreen(new ClickGuiScreen());
        super.onEnable();
    }

    //Rly Retarded Fix
    @DemoListen
    public void onTick(TickEvent event){
        if(CurryMod.featureManager.getHack("HudEditor").isEnabled()){
            toggle();
        }
    }
}
