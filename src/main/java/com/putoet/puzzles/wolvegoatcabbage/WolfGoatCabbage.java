package com.putoet.puzzles.wolvegoatcabbage;

import static com.putoet.search.GenericSearch.Node;
import static com.putoet.search.GenericSearch.bfs;

import java.util.*;

/**
 * A man finds himself on a riverbank with a wolf, a goat, and a head of cabbage. He needs to transport all three to
 * the other side of the river in his boat. However, the boat has room for only the man himself and one other item
 * (either the wolf, the goat, or the cabbage). In his absence, the wolf would eat the goat, and the goat would eat
 * the cabbage. Show how the man can get all these “passengers” to the other side.”
 */
public class WolfGoatCabbage {
    /**
     * Search State contains the location of the boat, wolf, goat and cabbage
     *
     * A value of true means the item is on the west bank
     * A value of false means the item is on the east bank
     */
    private record State(boolean boat, boolean wolf, boolean goat, boolean cabbage) {
    }

    /**
     * success returns true if all items are on the east bank *all attributes are false)
     */
    private static boolean success(State state) {
        return !state.boat() && !state.wolf() && !state.goat() && !state.cabbage();
    }

    /**
     * if the boat is on the west bank:
     *      wolf and goat cannot be without the cabbage on the east bank AND
     *      goat and cabbage cannot be without the wolf on the east bank
     * else
     *      wolf and goat cannot be without the cabbage on the west bank AND
     *      goat and cabbage cannot be without the wolf on the west bank
     */
    public static boolean isValid(State state) {
        return state.boat() ?
                !(!state.wolf() && !state.goat() && state.cabbage()) && !(!state.goat() && !state.cabbage() && state.wolf())
                :
                !(state.wolf() && state.goat() && !state.cabbage()) && !(state.goat() && state.cabbage() && !state.wolf());
    }

    /**
     * The boat can always cross empty (containing nothing) or the boat can take one of the items from the bank where
     * the boat is. The resulting list must be filtered, so it doesn;t contain invalid states.
     */
    public static List<State> successors(State state) {
        final List<State> options = new ArrayList<>();
        final boolean boat = !state.boat();

        // cross with an empty boat
        options.add(new State(boat, state.wolf(), state.goat(), state.cabbage()));

        // cross with just the wolf
        if (state.wolf() == state.boat())
            options.add(new State(boat, !state.wolf(), state.goat(), state.cabbage()));

        // cross with just the goat
        if (state.goat() == state.boat())
            options.add(new State(boat, state.wolf(), !state.goat(), state.cabbage()));

        // cross with just the cabbage
        if (state.cabbage() == state.boat())
            options.add(new State(boat, state.wolf(), state.goat(), !state.cabbage()));

        // return a list of only valid states
        return options.stream().filter(WolfGoatCabbage::isValid).toList();
    }

    public static void main(String[] args) {
        final State initial = new State(true, true, true, true);
        final Optional<Node<State>> node = bfs(initial, WolfGoatCabbage::success, WolfGoatCabbage::successors);

        node.ifPresent(nodeState -> WolfGoatCabbage.path(nodeState.path()));
    }

    public static void path(List<State> path) {
        for (int idx = 0; idx < path.size() - 1; idx++) {
            crossing(path.get(idx), path.get(idx + 1));
        }
    }

    public static void crossing(State current, State next) {
        System.out.printf("Boat is crossing to the %s taking %s\n",
                current.boat() ? "EAST" : "WEST", taking(current, next));
    }

    private static String taking(State current, State next) {
        if (current.wolf() ^ next.wolf()) return "WOLF";
        if (current.goat() ^ next.goat()) return "GOAT";
        if (current.cabbage() ^ next.cabbage()) return "CABBAGE";

        return "NOTHING";
    }
}
