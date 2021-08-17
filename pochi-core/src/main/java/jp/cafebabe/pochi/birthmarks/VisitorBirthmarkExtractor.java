package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.pochi.util.VisitorSupplier;
import org.objectweb.asm.ClassVisitor;

public class VisitorBirthmarkExtractor<T> extends AbstractExtractor {
    private VisitorSupplier<PochiClassVisitor<T>> visitorSupplier;

    public VisitorBirthmarkExtractor(BirthmarkType type, Configuration config,
                                     VisitorSupplier<PochiClassVisitor<T>> visitorSupplier) {
        super(type, config);
        this.visitorSupplier = visitorSupplier;
    }

    public PochiClassVisitor<T> visitor(ClassVisitor parent) {
        return visitorSupplier.get(parent, type(), configuration());
    }
}
