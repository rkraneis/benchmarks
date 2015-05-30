/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hays.scratch.benchmarks;

/**
 *
 * @author rnk
 */
class PrimitiveVsWrapperLoop {

    private final int MAX_VALUE_PRIMITIVE_INT;
    private final long MAX_VALUE_PRIMITIVE_LONG;
    private final Integer MAX_VALUE_WRAPPER_INT;
    private final Long MAX_VALUE_WRAPPER_LONG;

    PrimitiveVsWrapperLoop(int maxValue) {
        MAX_VALUE_PRIMITIVE_INT = maxValue;
        MAX_VALUE_PRIMITIVE_LONG = maxValue;
        MAX_VALUE_WRAPPER_INT = maxValue;
        MAX_VALUE_WRAPPER_LONG = (long) maxValue;
    }

    long primitiveLoopLong() {
        long sum = 0L;
        for (long l = 0; l <= MAX_VALUE_PRIMITIVE_LONG; l++) {
            sum += l;
        }
        return sum;
    }

    Long wrapperLoopLong() {
        Long sum = 0L;
        for (long l = 0; l <= MAX_VALUE_PRIMITIVE_LONG; l++) {
            sum += l;
        }
        return sum;
    }

    Long wrapperLoopLongLong() {
        Long sum = 0L;
        for (Long l = 0L; l <= MAX_VALUE_WRAPPER_LONG; l++) {
            sum += l;
        }
        return sum;
    }

    int primitiveLoopInt() {
        int sum = 0;
        for (int i = 0; i <= MAX_VALUE_PRIMITIVE_INT; i++) {
            sum += i;
        }
        return sum;
    }

    Integer wrapperLoopInt() {
        Integer sum = 0;
        for (int i = 0; i <= MAX_VALUE_PRIMITIVE_INT; i++) {
            sum += i;
        }
        return sum;
    }

    Integer wrapperLoopIntInt() {
        Integer sum = 0;
        for (Integer i = 0; i <= MAX_VALUE_WRAPPER_INT; i++) {
            sum += i;
        }
        return sum;
    }

    CustomInteger customIntegerLoop() {
        CustomInteger sum = CustomInteger.of(1);
        for (int i = 0; i < MAX_VALUE_PRIMITIVE_INT; i++) {
            sum = sum.add(i);
        }
        return sum;
    }

    public static class CustomInteger {

        private final int value;

        private CustomInteger(int i) {
            this.value = i;
        }

        public static CustomInteger of(int i) {
            return new CustomInteger(i);
        }

        public CustomInteger add(int i) {
            return new CustomInteger(this.value + i);
        }
    }
}
