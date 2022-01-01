package com.putoet.puzzles.bridgecrossing;

import java.util.*;

public class Crossing {
    public static void main(String[] args) {
        final Crossing crossing = new Crossing();
        final Optional<State> solution = crossing.solve();

        if (solution.isEmpty())
            System.out.println("No solution found");
        else
            System.out.println("People crossed in just " + solution.get().totalTimeCrossed() + " minutes");
    }

    public State initial() {
        return new State(
                Set.of(Person.ONE, Person.TWO, Person.THREE, Person.FOUR),
                Set.of(),
                0,
                true);
    }

    public List<State> successors(State state) {
        if (state.flashLightAtTheLeft())
            return successorsFromLeftToRight(state);

        return successorsFromRightToLeft(state);
    }

    private List<State> successorsFromLeftToRight(State state) {
        final List<State> next = new ArrayList<>();
        for (Person first : state.left()) {
            for (Person second : state.left()) {
                if (first.equals(second))
                    continue;
                final Set<Person> left = new HashSet<>(state.left());
                final Set<Person> right = new HashSet<>(state.right());
                final int timeToCross = Math.max(first.timeToCross(), second.timeToCross());
                left.remove(first);
                right.add(first);
                left.remove(second);
                right.add(second);

                final State nextState = new State(left,
                        right,
                        state.totalTimeCrossed() + timeToCross,
                        false);
                if (!next.contains(nextState))
                    next.add(nextState);
            }
        }
        return next;
    }

    private List<State> successorsFromRightToLeft(State state) {
        final List<State> next = new ArrayList<>();
        for (Person first : state.right()) {
            final Set<Person> left = new HashSet<>(state.left());
            final Set<Person> right = new HashSet<>(state.right());
            final int timeToCross = first.timeToCross();

            left.add(first);
            right.remove(first);
            final State nextState = new State(left,
                    right,
                    state.totalTimeCrossed() + timeToCross,
                    true);
            if (!next.contains(nextState))
                next.add(nextState);
        }
        return next;
    }

    public boolean goalTest(State state) {
        return state.left().isEmpty() &&
                state.right().containsAll(Set.of(Person.ONE, Person.TWO, Person.THREE, Person.FOUR)) &&
                !state.flashLightAtTheLeft();
    }

    public Optional<State> solve() {
        final Set<State> visited = new HashSet<>();
        final PriorityQueue<State> queue = new PriorityQueue<>();
        queue.offer(initial());

        while (!queue.isEmpty()) {
            final State current = queue.poll();
            if (goalTest(current))
                return Optional.of(current);

            for (State next : successors(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return Optional.empty();
    }

    public enum Person {
        ONE(1),
        TWO(2),
        THREE(5),
        FOUR(10);

        private final int timeToCross;

        Person(int timeToCross) {
            this.timeToCross = timeToCross;
        }

        int timeToCross() {
            return timeToCross;
        }
    }

    public record State(Set<Person> left, Set<Person> right, int totalTimeCrossed,
                        boolean flashLightAtTheLeft) implements Comparable<State> {
        @Override
        public int compareTo(State other) {
            return Integer.compare(this.totalTimeCrossed, other.totalTimeCrossed);
        }
    }
}
