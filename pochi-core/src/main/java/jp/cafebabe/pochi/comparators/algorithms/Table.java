package jp.cafebabe.pochi.comparators.algorithms;

import java.util.stream.Stream;

class Table {
    private int[] table;
    private Size max;

    public Table(int width, int height) {
        table = new int[width * height];
        this.max = Size.of(width, height);
    }

    public int cost() {
        return table[table.length - 1];
    }

    public void set(int value, Index2D index) {
        table[index.compute(max)] = value;
    }

    public int get(Index2D index) {
        return table[index.compute(max)];
    }
    public int get(int x, int y) {
        return get(new Index2D(x, y));
    }

    public Stream<Index2D> indexStream() {
        return max.stream();
    }
}
