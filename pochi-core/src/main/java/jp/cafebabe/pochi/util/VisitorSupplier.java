package jp.cafebabe.pochi.util;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import org.objectweb.asm.ClassVisitor;

@FunctionalInterface
public interface VisitorSupplier<T> {
    T get(ClassVisitor parent, BirthmarkType type, Configuration config);
}
