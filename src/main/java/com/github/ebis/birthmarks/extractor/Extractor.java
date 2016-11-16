package com.github.ebis.birthmarks.extractor;

import java.io.IOException;
import java.io.InputStream;

import com.github.ebis.Context;
import com.github.ebis.ContextBuilder;
import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.BirthmarkException;
import com.github.ebis.birthmarks.BirthmarkType;
import com.github.ebis.birthmarks.Element;
import com.github.kunai.entries.Entry;

public interface Extractor<T> {
    /**
     * build birthmark element from given string.
     * 
     * @param value
     *            birthmark element string representation.
     * @return built element.
     */
    Element<T> buildElement(String value);

    Birthmark<T> parse(String line);

    /**
     * extract birthmark from given program.
     * 
     * @param in
     *            input stream for program.
     * @return extracted birthmark.
     * @throws BirthmarkException
     *             extraction failed.
     */
    Birthmark<T> extract(InputStream in, Context context) throws BirthmarkException;

    default Birthmark<T> extract(InputStream in) throws BirthmarkException{
        return extract(in, ContextBuilder.getDefault());
    }

    default Birthmark<T> extract(Entry entry, Context context) throws BirthmarkException{
        try(InputStream in = entry.getInputStream()){
            return extract(entry, context);
        } catch(IOException e){
            throw new BirthmarkException(e);
        }
    }

    /**
     * returns default parameters of this extractor. This method returns same
     * parameters, but different object.
     * 
     * @return
     */
    Parameters getDefaultParameters();

    /**
     * returns the name of this extractor.
     * 
     * @return the name of this extractor.
     */
    BirthmarkType getName();

    /**
     * returns current parameters.
     * 
     * @return current parameters.
     */
    Parameters getParameters();

    /**
     * Specify the parameters.
     */
    void setParameter(Parameters parameters);
}
