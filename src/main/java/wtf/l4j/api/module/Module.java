package wtf.l4j.api.module;

import lombok.Getter;
import lombok.Setter;

import net.minecraft.network.packet.Packet;

import wtf.l4j.api.module.option.Option;
import wtf.l4j.api.utils.text.ChatHelper;
import wtf.l4j.api.utils.MinecraftInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module extends ChatHelper implements MinecraftInterface {

    private final ModuleInfo module = getClass().getAnnotation(ModuleInfo.class);
    @Getter private List<Option> options = new ArrayList<>();
    @Getter @Setter private Category cat = module.category();
    @Getter @Setter private String name = module.name();
    @Getter @Setter private String desc = module.desc();
    @Getter @Setter private int key;
    @Getter private boolean enabled;
//    @Getter private boolean drawn;

    public void toggle(){
        enabled = !enabled;
        toggleMessage(this);
        if(enabled) onEnable();
        else onDisable();
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
        if(enabled) onEnable();
        else onDisable();
    }

    public boolean nullCheck(){
        return mc.player == null || mc.world == null;
    }

    public void addOptions(Option... options){
        this.options.addAll(Arrays.asList(options));
    }

    public String getContent() {
        return null;
    }

    public void sendPacket(Packet<?> p){
        mc.getNetworkHandler().sendPacket(p);
    }

    public void sendPacketNoEvent(Packet<?> p){
        mc.getNetworkHandler().getConnection().send(p);
    }

    public void onEnable(){}
    public void onDisable(){}

}
