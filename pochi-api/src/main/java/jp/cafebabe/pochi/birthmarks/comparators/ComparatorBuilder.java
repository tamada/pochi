package jp.cafebabe.pochi.birthmarks.comparators;

import jp.cafebabe.pochi.birthmarks.TaskBuilder;
import jp.cafebabe.pochi.birthmarks.config.Configuration;

public interface ComparatorBuilder extends TaskBuilder<ComparatorType> {
    @Override
    ComparatorType type();

    @Override
    Comparator build(Configuration config);
}
