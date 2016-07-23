package com.github.ebis.birthmarks.extractor;

import java.io.InputStream;

import com.github.ebis.Context;
import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.BirthmarkException;
import com.github.ebis.birthmarks.Element;
import com.github.ebis.birthmarks.Parameters;

public abstract class AbstractExtractor<T> implements Extractor<T> {
    private Parameters defaultParameters = new Parameters();
    private String name;
    private Parameters parameters;

    public AbstractExtractor(String name) {
        this.name = name;

        initialize();
    }

    @Override
    public abstract Element<T> buildElement(String value);

    @Override
    public abstract Birthmark<T> extract(InputStream in, Context context) throws BirthmarkException;

    @Override
    public Parameters getDefaultParameters() {
        return new Parameters(defaultParameters);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Parameters getParameters() {
        return parameters;
    }

    private void initialize() {
        initParameters(defaultParameters);

        parameters = new Parameters(defaultParameters);
    }

    /**
     * initialize default parameters. Default parameter is updated by updating
     * ``defaultParameters.'' Override the method in a sub class.
     * 
     * @param defaultParameters
     */
    protected void initParameters(Parameters defaultParameters) {
        // do nothing.
    }

    /**
     * 
     */
    @Override
    public void setParameter(Parameters parameters) {
        this.parameters = parameters;
    }

}
