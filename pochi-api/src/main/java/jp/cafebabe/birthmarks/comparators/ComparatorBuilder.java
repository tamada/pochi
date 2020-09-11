package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;

public interface ComparatorBuilder extends TaskBuilder<ComparatorType> {
    @Override
    ComparatorType type();

    @Override
    Comparator build(Configuration config);
}
