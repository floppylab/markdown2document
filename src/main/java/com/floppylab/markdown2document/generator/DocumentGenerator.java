package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;

public abstract class DocumentGenerator {

    /**
     * Generates a document from the given document descriptor.
     *
     * @param document document descriptor object
     * @return output file
     * @throws DocumentGeneratorException if a low-level problem occurs
     */
    public abstract Output generate(Document document) throws DocumentGeneratorException;
}
