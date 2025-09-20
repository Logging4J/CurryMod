package dev.l4j.currymod.client.module.option;

import com.google.gson.JsonObject;
import dev.l4j.currymod.util.MinecraftInterface;
import lombok.Getter;

import java.util.HashSet;
import java.util.function.Consumer;

@Getter
public abstract class Option<T> implements MinecraftInterface {
    private HashSet<Consumer<T>> updates;
    private final String name;
    private final T defaultValue;
    private T value;

    public Option(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public Option(String name, T defaultValue,  Consumer<T> update) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        updates = new HashSet<>();

        updates.add(update);
    }

    public void update() {
        if (updates != null && !nullCheck()) {
            updates.forEach(update -> {
                if (update != null) {
                    update.accept(value);
                }
            });
        }
    }

    public void setValue(T value) {
        if (isValid(value)) {
            this.value = value;
        }
        update();
    }

    protected abstract boolean isValid(T value);
}
