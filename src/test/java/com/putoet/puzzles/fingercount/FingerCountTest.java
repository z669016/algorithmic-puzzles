package com.putoet.puzzles.fingercount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FingerCountTest {

    @Test
    void fingerForCount() {
        assertEquals(FingerCount.THUMB, FingerCount.fingerForCount(1));
        assertEquals(FingerCount.FIRST, FingerCount.fingerForCount(10));
        assertEquals(FingerCount.MIDDLE, FingerCount.fingerForCount(3));
        assertEquals(FingerCount.RING, FingerCount.fingerForCount(12));
        assertEquals(FingerCount.LITTLE, FingerCount.fingerForCount(5));
        assertEquals(FingerCount.RING, FingerCount.fingerForCount(14));
        assertEquals(FingerCount.MIDDLE, FingerCount.fingerForCount(7));
        assertEquals(FingerCount.FIRST, FingerCount.fingerForCount(18));
        assertEquals(FingerCount.THUMB, FingerCount.fingerForCount(9));

        assertEquals(FingerCount.FIRST, FingerCount.fingerForCount(1000));
    }
}