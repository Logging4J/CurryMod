package club.l4j.currymod.feature.core;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.feature.options.Option;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hack {

    private Construct construct = getClass().getAnnotation(Construct.class);
    protected MinecraftClient mc = MinecraftClient.getInstance();
    private List<Option> options = new ArrayList<>();
    private String name = construct.name();
    private String desc = construct.description();
    private Category category = construct.category();
    private boolean enabled;
    private int key;
    private boolean drawn;

    public List<Option> getOptions(){
        return options;
    }

    public void addOptions(Option... options){
        this.options.addAll(Arrays.asList(options));
    }

    public void toggle(){
        enabled = !enabled;
        if (enabled){
            onEnable();
        }else{
            onDisable();
        }
    }

    public void onEnable(){
        CurryMod.EVENT_BUS.register(this);
    }
    public void onDisable(){
        CurryMod.EVENT_BUS.unregister(this);
    }

    public String getContent(){
        return null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public void sendPacket(Packet<?> packet){
        mc.getNetworkHandler().sendPacket(packet);
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled){
            onEnable();
        }else {
            onDisable();
        }
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
