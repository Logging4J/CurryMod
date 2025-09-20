package dev.l4j.currymod.client.module;

import dev.l4j.currymod.client.module.option.Option;
import dev.l4j.currymod.client.module.option.options.OptionKeybind;
import dev.l4j.currymod.util.MinecraftInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Module implements MinecraftInterface {

    private final List<Option<?>> options;
    private final OptionKeybind keybind;
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    @Setter private boolean shown;

    public Module() {
        this.name = this.getConstructor().name();
        this.description = this.getConstructor().description();
        this.category = this.getConstructor().category();
        this.enabled = this.getConstructor().enabled();
        this.shown = this.getConstructor().shown();

        this.options = new ArrayList<>();

        this.keybind = new OptionKeybind(InputUtil.fromKeyCode(GLFW.GLFW_KEY_UNKNOWN, 0));
        addOptions(keybind);
    }

    protected abstract void onEnable();
    protected abstract void onDisable();

    public Info getConstructor() {
        return this.getClass().getAnnotation(Info.class);
    }

    public void addOptions(Option<?>... options) {
        this.options.addAll(Arrays.asList(options));
    }

    public void toggle() {
        this.setEnabled(!this.isEnabled());
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public final String getDisplayName() {
        return getDisplayInfo().isBlank()
                ? this.name
                : this.name + Formatting.GRAY + " [" + Formatting.WHITE + this.getDisplayInfo() + Formatting.GRAY + "]";
    }

    public String getDisplayInfo() {
        return "";
    }

    public void send(Packet<?> packet) {
        if (mc.getNetworkHandler() == null) return;

        mc.getNetworkHandler().sendPacket(packet);
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String name();
        String description();
        Category category();
        boolean enabled() default false;
        boolean shown() default true;
    }

    @Getter
    @AllArgsConstructor
    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        VISUAL("Visual"),
        EXPLOIT("Exploit"),
        MISC("Misc"),
        CLIENT("Client"),
        CHAT("Chat"),
        FUN("Fun");

        final String name;
    }
}
