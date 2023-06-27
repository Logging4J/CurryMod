package club.l4j.currymod.graphics.hudeditor.element;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.util.IGlobals;
import lombok.Getter;
import lombok.Setter;

public class HudElement implements IGlobals {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String desc;

    @Getter @Setter
    private int height;

    @Getter @Setter
    private int width;

    @Getter @Setter
    private int x = 1;

    @Getter @Setter
    private int y = 1;

    @Getter
    private boolean enabled = false;

    public HudElement(String name) {
        this.name = name;
    }

    public void toggle(){
        enabled = !enabled;
        if(enabled){
            onEnable();
        }else {
            onDisable();
        }
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
        if(enabled){
            onEnable();
        }else {
            onDisable();
        }
    }

    public void onEnable(){
        CurryMod.EVENT_BUS.register(this);
    }

    public void onDisable(){
        CurryMod.EVENT_BUS.unregister(this);
    }

}
