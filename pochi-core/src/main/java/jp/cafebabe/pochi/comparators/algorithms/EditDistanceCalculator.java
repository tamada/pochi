package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.pochi.util.Tuple;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class EditDistanceCalculator<T> {
    private static final int BIG_NUMBER = 10;

    public int compute(List<T> list1, List<T> list2) {
        Table table = initTable(list1.size(), list2.size());
        computeCosts(table, list1, list2);
        return table.cost();
    }

    private void computeCosts(Table table, List<T> list1, List<T> list2) {
        table.indexStream()
                .forEach(index -> findPair(index, list1, list2)
                        .ifPresent(pair -> computeCost(table, index, pair)));
    }

    private Optional<Tuple<T>> findPair(Index2D index, List<T> list1, List<T> list2) {
        if(index.x() == 0 || index.y() == 0)
            return Optional.empty();
        return Optional.of(Tuple.of(list1.get(index.x() - 1), list2.get(index.y() - 1)));
    }

    private void computeCost(Table table, Index2D index, Tuple<T> pair) {
        int newValue = computeCostImpl(table, index, pair);
        table.set(newValue, index);
    }

    private int computeCostImpl(Table table, Index2D index, Tuple<T> pair) {
        int d1 = tableValue(table, index.relativeOf(-1,  0)) + 1;
        int d2 = tableValue(table, index.relativeOf( 0, -1)) + 1;
        int d3 = tableValue(table, index.relativeOf(-1, -1)) +
                pair.ifEquals((l, r) -> l == r, 0, 1);
        return minimum(d1, d2, d3);
    }

    private int minimum(int... array) {
        return IntStream.of(array)
                .reduce(BIG_NUMBER, (p, n) -> Math.min(p, n));
    }

    private int tableValue(Table table, Optional<Index2D> optionalIndex) {
        return optionalIndex.map(index -> table.get(index))
                .orElse(BIG_NUMBER);
    }

    private Table initTable(int width, int height) {
        Table table = new Table(width + 1, height + 1);
        IntStream.rangeClosed(0, width)
                .forEach(x -> table.set(x, new Index2D(x, 0)));
        IntStream.rangeClosed(0, height)
                .forEach(y -> table.set(y, new Index2D(0, y)));
        return table;
    }
}
