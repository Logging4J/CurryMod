package club.l4j.currymod.feature.options.impl;

import club.l4j.currymod.feature.options.Option;

public class OptionSlider extends Option {

    private double min, max, increment;
    private double value;

    public OptionSlider(String name, double min, double max, double increment, double defVal) {
        super(name);
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.value = defVal;
    }

    public static double clamp(double value, double min, double max){
        if(value>max){value=max;}
        if(value<min){value=min;}
        return value;
    }


    public double getValue(){
        return value;
    }

    public float getFloatValue(){
        return (float) value;
    }

    public int getIntValue(){
        return (int) value;
    }

    public long getLongValue(){
        return (long) value;
    }

    public double getIncrement() {
        return increment;
    }

    public void setValue(double value){
        value = clamp(value, min, max);
        value = Math.round(value *(1/increment)) / (1 / increment);
        this.value = value;
    }

    public void increment(boolean positive){
        if(positive){
            setValue(getValue() + getIncrement());
        }else {
            setValue(getValue() - getIncrement());
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
