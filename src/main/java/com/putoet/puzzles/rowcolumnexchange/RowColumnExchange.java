package com.putoet.puzzles.rowcolumnexchange;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RowColumnExchange {
    public static boolean isRowColumnExchange(int[][] tableOne, int[][] tableTwo) {
        assert isSquare(tableOne);
        assert isSquare(tableTwo);

        return rows(tableOne).equals(rows(tableTwo)) && columns(tableOne).equals(columns(tableTwo));
    }

    private static boolean isSquare(int[][] table) {
        int rows = table.length;
        assert rows > 0;

        for (int[] row : table)
            assert row.length  == rows;

        return true;
    }

    protected static Set<Set<Integer>> rows(int[][] table) {
        return Arrays.stream(table)
                .map(row -> Arrays.stream(row).boxed().collect(Collectors.toSet()))
                .collect(Collectors.toSet());
    }

    protected static Set<Set<Integer>> columns(int[][] table) {
        return IntStream.range(0, table.length)
                .mapToObj(column -> Arrays.stream(table).map(ints -> ints[column]).mapToInt(i -> i).boxed().collect(Collectors.toSet()))
                .collect(Collectors.toSet());
    }
}
