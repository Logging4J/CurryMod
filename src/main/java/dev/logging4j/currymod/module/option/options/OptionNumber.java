package dev.logging4j.currymod.module.option.options;

import dev.logging4j.currymod.module.option.Option;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class OptionNumber<T extends Number> extends Option<T> {
    private final T min, max, increment;

    public OptionNumber(String name, T defaultValue, T min, T max, T increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public OptionNumber(String name, T defaultValue, T min, T max, T increment, Consumer<T> update) {
        super(name, defaultValue, update);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    @Override
    protected boolean isValid(T value) {
        if (value == null) return false;

        double val = value.doubleValue();
        double minVal = min.doubleValue();
        double maxVal = max.doubleValue();

        return val >= minVal && val <= maxVal;
    }

    @SuppressWarnings("unchecked")
    public void increment() {
        if (getValue() instanceof Integer) {
            int newValue = getValue().intValue() + increment.intValue();
            if (newValue <= max.intValue()) {
                setValue((T) Integer.valueOf(newValue));
            }
        } else if (getValue() instanceof Float) {
            float newValue = getValue().floatValue() + increment.floatValue();
            if (newValue <= max.floatValue()) {
                setValue((T) Float.valueOf(newValue));
            }
        } else if (getValue() instanceof Double) {
            double newValue = getValue().doubleValue() + increment.doubleValue();
            if (newValue <= max.doubleValue()) {
                setValue((T) Double.valueOf(newValue));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void decrement() {
        if (getValue() instanceof Integer) {
            int newValue = getValue().intValue() - increment.intValue();
            if (newValue >= min.intValue()) {
                setValue((T) Integer.valueOf(newValue));
            }
        } else if (getValue() instanceof Float) {
            float newValue = getValue().floatValue() - increment.floatValue();
            if (newValue >= min.floatValue()) {
                setValue((T) Float.valueOf(newValue));
            }
        } else if (getValue() instanceof Double) {
            double newValue = getValue().doubleValue() - increment.doubleValue();
            if (newValue >= min.doubleValue()) {
                setValue((T) Double.valueOf(newValue));
            }
        }
    }
}
