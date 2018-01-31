package com.github.pochi.izumo.builders;

import java.util.Objects;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.izumo.CorrespondenceChecker;
import com.github.pochi.izumo.GuessedPairMatcher;
import com.github.pochi.izumo.PairMatcher;
import com.github.pochi.izumo.PairMatcherType;

public class GuessedPairMatcherBuilder<T> extends AbstractPairMatcherBuilder<T>{
    private CorrespondenceChecker<T> checker;

    public GuessedPairMatcherBuilder() {
        this((item1, item2) -> Objects.equals(item1, item2));
    }

    public GuessedPairMatcherBuilder(CorrespondenceChecker<T> checker) {
        super(new PairMatcherType("Guessed"));
        this.checker = checker;
    }

    @Override
    public PairMatcher<T> build(Configuration config) {
        return new GuessedPairMatcher<>(type(), config, checker);
    }
}
