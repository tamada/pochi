package com.github.ebis.birthmarks.extractor;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

import com.github.ebis.Context;
import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.BirthmarkException;
import com.github.ebis.birthmarks.BirthmarkType;
import com.github.ebis.birthmarks.Element;

public abstract class ASMExtractor<T> extends AbstractExtractor<T> {
    public ASMExtractor(BirthmarkType name) {
        super(name);
    }

    @Override
    public abstract Element<T> buildElement(String value);

    @Override
    public Birthmark<T> extract(InputStream in, Context context) throws BirthmarkException {
        try {
            ClassReader reader = new ClassReader(in);
            EbisClassVisitor<T> visitor = getVisitor(context);

            reader.accept(visitor, 0);

            return visitor.getBirthmark();
        } catch (IOException e) {
            throw new BirthmarkException(e);
        }
    }

    public abstract EbisClassVisitor<T> getVisitor(Context context);
}
