package club.l4j.currymod.feature.core;

import club.l4j.currymod.util.IGlobals;
import club.l4j.currymod.util.TextUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Command implements IGlobals {

    private Construct c = getClass().getAnnotation(Construct.class);
    private String description = c.description();
    private String[] alias = c.alias();
    private String name = c.name();

    public void onTrigger(String arguments) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Construct {
        String name();
        String description();
        String[] alias() default {};
    }
}
