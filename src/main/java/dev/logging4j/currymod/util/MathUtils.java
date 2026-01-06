package dev.logging4j.currymod.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@UtilityClass
public class MathUtils {

    public static final Random RANDOM = new Random();

    public static int random(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

    public double roundToPlace(double value, int place, RoundingMode roundingMode){
        if(place < 0){
            return value;
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, roundingMode);
        return bd.doubleValue();
    }
}
