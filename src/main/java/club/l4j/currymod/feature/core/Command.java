package club.l4j.currymod.feature.core;

import club.l4j.currymod.util.IGlobals;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Command implements IGlobals {

    private Construct c = getClass().getAnnotation(Construct.class);

    @Getter @Setter
    private String description = c.description();

    @Getter @Setter
    private String[] alias = c.alias();

    @Getter @Setter
    public String usage = c.usage();

    @Getter @Setter
    private String name = c.name();

    @Getter @Setter
    public static String prefix = "@";

    public void onTrigger(String arguments) {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Construct {
        String name();
        String usage();
        String[] alias();
        String description();
    }
}
