package com.putoet.puzzles.fingercount;

public class FingerCount {
    public static final String THUMB = "thumb";
    public static final String FIRST = "first finger";
    public static final String MIDDLE = "middle finger";
    public static final String RING = "ring finger";
    public static final String LITTLE = "little finger";

    public static String fingerForCount(int count) {
        return switch (numberForCount(count)) {
            case 0 -> THUMB;
            case 1, 7 -> FIRST;
            case 2, 6 -> MIDDLE;
            case 3, 5 -> RING;
            default -> LITTLE;
        };
    }

    public static int numberForCount(int count) {
        return (count - 1) % 8;
    }
}
