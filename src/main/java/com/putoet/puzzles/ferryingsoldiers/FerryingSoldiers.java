package com.putoet.puzzles.ferryingsoldiers;

public class FerryingSoldiers {
    public record State(int soldiersLeft,
                        int soldiersRight,
                        int boyeLeft,
                        int boysRight,
                        boolean boatLeft,
                        int crossings) implements Comparable<State>{

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.crossings, other.crossings);
        }
    }

    public static State initial() {
        return null;
    }
}
