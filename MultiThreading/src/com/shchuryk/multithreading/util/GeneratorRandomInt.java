package com.shchuryk.multithreading.util;

import com.shchuryk.multithreading.exception.GeneratorRandomException;

public class GeneratorRandomInt {
    public static int generateRandomInt(int min, int max) throws GeneratorRandomException {
        if (min > max)
            throw new GeneratorRandomException("min value can not be greater than max value");
        if (min == max)
            return min;
        return min + (int) (Math.random() * max);
    }
}
