package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.pochi.util.VisitorSupplier;
import org.objectweb.asm.ClassVisitor;

public class VisitorBirthmarkExtractor extends AbstractExtractor {
    private VisitorSupplier<? extends PochiClassVisitor> visitorSupplier;

    public VisitorBirthmarkExtractor(BirthmarkType type, Configuration config,
                                     VisitorSupplier<? extends PochiClassVisitor> visitorSupplier) {
        super(type, config);
        this.visitorSupplier = visitorSupplier;
    }

    public PochiClassVisitor visitor(ClassVisitor parent) {
        return visitorSupplier.get(parent, type(), configuration());
    }
}
