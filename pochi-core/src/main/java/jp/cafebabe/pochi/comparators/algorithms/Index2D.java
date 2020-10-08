package jp.cafebabe.pochi.comparators.algorithms;

import java.util.Optional;

class Index2D {
    private int x, y;

    public Index2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Optional<Index2D> relativeOf(int deltaX, int deltaY) {
        if((x + deltaX) < 0 || (y + deltaY) < 0)
            return Optional.empty();
        return Optional.of(new Index2D(x + deltaX, y + deltaY));
    }

    public int compute(Size max) {
        return y * max.width() + x;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Index2D
                && equals((Index2D)object);
    }

    public boolean equals(Index2D index) {
        return x() == index.x()
                && y() == index.y();
    }
}
