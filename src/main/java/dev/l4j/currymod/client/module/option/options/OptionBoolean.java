package dev.l4j.currymod.client.module.option.options;

import dev.l4j.currymod.client.module.option.Option;

import java.util.function.Consumer;

public class OptionBoolean extends Option<Boolean> {

    public OptionBoolean(String name, Boolean defaultValue) {
        super(name, defaultValue);
    }

    public OptionBoolean(String name, Boolean defaultValue, Consumer<Boolean> update) {
        super(name, defaultValue, update);
    }

    public void toggle() {
        this.setValue(!this.getValue());
    }

    @Override
    protected boolean isValid(Boolean value) {
        return true;
    }
}
