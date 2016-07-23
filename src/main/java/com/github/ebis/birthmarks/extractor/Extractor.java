package com.github.ebis.birthmarks.extractor;

import java.io.InputStream;

import com.github.ebis.Context;
import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.BirthmarkException;
import com.github.ebis.birthmarks.Element;
import com.github.ebis.birthmarks.Parameters;

public interface Extractor<T> {
    /**
     * build birthmark element from given string.
     * 
     * @param value
     *            birthmark element string representation.
     * @return built element.
     */
    Element<T> buildElement(String value);

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
    ExtractorName getName();

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
