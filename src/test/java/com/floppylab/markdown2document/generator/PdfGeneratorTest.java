package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import com.floppylab.markdown2document.exception.DocumentIsNullException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PdfGeneratorTest {

    private final PdfGenerator pdfGenerator = new PdfGenerator();

    @Test(expected = DocumentIsNullException.class)
    public void givenNullDocumentThenExceptionExpected() throws DocumentGeneratorException {
        pdfGenerator.generate(null);
    }

    @Test
    public void givenDocumentThenPdfGeneratedSuccessfully() throws DocumentGeneratorException {
        final Document document = Document.builder().build();
        final Output output = pdfGenerator.generate(document);
        assertNotNull(output);
    }

}