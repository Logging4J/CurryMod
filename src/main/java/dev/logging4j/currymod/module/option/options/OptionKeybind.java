package dev.logging4j.currymod.module.option.options;

import dev.logging4j.currymod.module.option.Option;
import net.minecraft.client.util.InputUtil;

import java.util.function.Consumer;

public class OptionKeybind extends Option<InputUtil.Key> {

    public OptionKeybind(String name, InputUtil.Key defaultValue, Consumer<InputUtil.Key> update) {
        super(name, defaultValue, update);
    }

    public OptionKeybind(InputUtil.Key defaultValue) {
        super("Key", defaultValue);
    }

    @Override
    protected boolean isValid(InputUtil.Key value) {
        return true;
    }
}
