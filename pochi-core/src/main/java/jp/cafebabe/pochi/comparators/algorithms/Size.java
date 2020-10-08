package jp.cafebabe.pochi.comparators.algorithms;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class Size {
    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Stream<Index2D> stream() {
        return IntStream.range(0, width())
                .mapToObj(x -> Integer.valueOf(x))
                .flatMap(x -> yStream(x));
    }

    private Stream<Index2D> yStream(int x) {
        return IntStream.range(0, height())
                .mapToObj(y -> new Index2D(x, y));
    }

    public static Size of(int maxWidth, int maxHeight) {
        return new Size(maxWidth, maxHeight);
    }
}
