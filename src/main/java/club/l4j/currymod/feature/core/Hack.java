package club.l4j.currymod.feature.core;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.options.Option;

import club.l4j.currymod.util.IGlobals;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hack implements IGlobals {

    private Construct construct = getClass().getAnnotation(Construct.class);

    private List<Option> options = new ArrayList<>();

    @Getter @Setter
    private String name = construct.name();

    @Getter @Setter
    private String desc = construct.description();

    @Getter @Setter
    private Category category = construct.category();

    @Getter @Setter
    private int key;

    @Getter @Setter
    private boolean drawn;

    private boolean enabled;

    public List<Option> getOptions(){
        return options;
    }

    public void addOptions(Option... options){
        this.options.addAll(Arrays.asList(options));
    }

    public String getContent(){
        return null;
    }


    public void toggle(){
        enabled = !enabled;
        if (enabled){
            onEnable();
        }else{
            onDisable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled){
            onEnable();
        }else {
            onDisable();
        }
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public void onEnable(){
        CurryMod.EVENT_BUS.register(this);
    }
    public void onDisable(){
        CurryMod.EVENT_BUS.unregister(this);
    }

    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        VISUAL("Visual"),
        EXPLOITS("Exploits"),
        MISC("Misc"),
        CLIENT("Client");

        private String name;
        Category(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Construct {
        String name();
        String description();
        Category category();
    }
}
