package com.putoet.puzzles.rowcolumnexchange;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RowColumnExchangeTest {
    private static int[][] TABLE_ONE = new int[][] {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
    };

    private static int[][] TABLE_ONE_MIXED = new int[][] {
            {11, 10, 9, 12},
            {7, 6, 5, 8},
            {3, 2, 1, 4},
            {15, 14, 13, 16}
    };

    private static int[][] TABLE_TWO = new int[][] {
            {12, 10, 11, 9},
            {16, 14, 5, 13},
            {8, 6, 7, 15},
            {4, 2, 3, 1}
    };

    @Test
    void noSquare() {
        assertThrows(AssertionError.class, () -> RowColumnExchange.isRowColumnExchange(new int[0][0], new int[0][0]));
        assertThrows(AssertionError.class, () -> RowColumnExchange.isRowColumnExchange(new int[2][3], new int[3][3]));
        assertThrows(AssertionError.class, () -> RowColumnExchange.isRowColumnExchange(new int[3][3], new int[0][2]));
        assertThrows(AssertionError.class, () -> RowColumnExchange.isRowColumnExchange(new int[3][3], new int[3][0]));
    }

    @Test
    void isRowColumnExchange() {
        assertFalse(RowColumnExchange.isRowColumnExchange(TABLE_ONE, TABLE_TWO));
        assertTrue(RowColumnExchange.isRowColumnExchange(TABLE_ONE, TABLE_ONE_MIXED));
    }

    @Test
    void rows() {
        final Set<Set<Integer>> rows = Set.of(
                Set.of(1, 2, 3, 4), Set.of(5, 6, 7, 8), Set.of(9, 10, 11, 12), Set.of(13, 14, 15, 16)
        );

        assertEquals(rows, RowColumnExchange.rows(TABLE_ONE));
    }

    @Test
    void columns() {
        final Set<Set<Integer>> columns = Set.of(
                Set.of(12, 16, 8, 4), Set.of(10, 14, 6, 2), Set.of(11, 5, 7, 3), Set.of(9, 13, 15, 1)
        );

        assertEquals(columns, RowColumnExchange.columns(TABLE_TWO));
    }
}