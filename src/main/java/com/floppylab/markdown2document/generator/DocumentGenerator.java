package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Input;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import java.util.ArrayList;
import java.util.List;

public abstract class DocumentGenerator {

    /**
     * Generates a document from the given document descriptor.
     *
     * @param document document descriptor object
     * @return output file
     * @throws DocumentGeneratorException if a low-level problem occurs
     */
    public abstract Output generate(Document document) throws DocumentGeneratorException;

    protected boolean isStyleMissing(Document document) {
        return document.getStyles() == null || document.getStyles().isEmpty();
    }

    protected Document addDefaultStyle(Document document) {
        List<Input> inputs = document.getStyles();
        if (inputs == null) {
            inputs = new ArrayList<Input>();
        }
        inputs.add(Input.DEFAULT);
        document.setStyles(inputs);
        return document;
    }
}
