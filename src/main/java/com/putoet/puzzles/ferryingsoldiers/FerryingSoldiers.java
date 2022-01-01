package com.putoet.puzzles.ferryingsoldiers;

import java.util.*;

public class FerryingSoldiers {
    public static void main(String[] args) {
        final Optional<State> solution = solve();
        if (solution.isEmpty())
            System.out.println("No solution found");
        else
            System.out.println("The river can be crossed in " + solution.get().crossings() + " crossings.");
    }

    public static State initial() {
        return new State(25, 0, 2, 0, true, 0);
    }

    public static boolean goalTest(State state) {
        return state.soldiersRight() == 25 &&
                (state.boysLeft() == 2 && state.boatLeft() || state.boysRight() == 2 && !state.boatLeft());
    }

    public static List<State> successors(State current) {
        final List<State> next = new ArrayList<>();
        if (current.boatLeft()) {
            if (current.soldiersLeft() > 0)
                next.add(new State(current.soldiersLeft() - 1,
                        current.soldiersRight() + 1,
                        current.boysLeft(),
                        current.boysRight(),
                        false,
                        current.crossings() + 1));
            if (current.boysLeft() > 1)
                next.add(new State(current.soldiersLeft(),
                        current.soldiersRight(),
                        current.boysLeft() - 2,
                        current.boysRight() + 2,
                        false,
                        current.crossings() + 1));
            if (current.boysLeft() > 0)
                next.add(new State(current.soldiersLeft(),
                        current.soldiersRight(),
                        current.boysLeft() - 1,
                        current.boysRight() + 1,
                        false,
                        current.crossings() + 1));
        } else {
            if (current.soldiersRight() > 0)
                next.add(new State(current.soldiersLeft() + 1,
                        current.soldiersRight() - 1,
                        current.boysLeft(),
                        current.boysRight(),
                        true,
                        current.crossings() + 1));
            if (current.boysRight() > 1)
                next.add(new State(current.soldiersLeft(),
                        current.soldiersRight(),
                        current.boysLeft() + 2,
                        current.boysRight() - 2,
                        true,
                        current.crossings() + 1));
            if (current.boysRight() > 0)
                next.add(new State(current.soldiersLeft(),
                        current.soldiersRight(),
                        current.boysLeft() + 1,
                        current.boysRight() - 1,
                        true,
                        current.crossings() + 1));
        }

        return next;
    }

    public static Optional<State> solve() {
        final Set<State> visited = new HashSet<>();
        final Queue<State> queue = new LinkedList<>();
        queue.offer(initial());

        while (!queue.isEmpty()) {
            final State current = queue.poll();

            if (goalTest(current))
                return Optional.of(current);

            final List<State> successors = successors(current);
            for (State next : successors) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return Optional.empty();
    }

    public record State(int soldiersLeft,
                        int soldiersRight,
                        int boysLeft,
                        int boysRight,
                        boolean boatLeft,
                        int crossings) implements Comparable<State> {

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.crossings, other.crossings);
        }
    }
}
