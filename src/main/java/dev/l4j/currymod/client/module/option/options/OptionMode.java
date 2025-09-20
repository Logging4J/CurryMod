package dev.l4j.currymod.client.module.option.options;

import com.google.gson.JsonObject;
import dev.l4j.currymod.client.module.option.Option;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class OptionMode extends Option<String> {

    private final List<String> modes;

    public OptionMode(String name, String defaultValue, String... modes) {
        super(name, defaultValue);
        this.modes = Arrays.asList(modes);
    }

    public OptionMode(String name, String defaultValue, Consumer<String> update, String... modes) {
        super(name, defaultValue, update);
        this.modes = Arrays.asList(modes);
    }

    @Override
    protected boolean isValid(String value) {
        return modes.contains(value);
    }
}
