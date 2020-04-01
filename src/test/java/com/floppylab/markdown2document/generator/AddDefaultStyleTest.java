/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Input;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import com.floppylab.markdown2document.exception.DocumentIsNullException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author brave
 */
public class AddDefaultStyleTest {
    
    private final PdfGenerator pdfGenerator = new PdfGenerator();
    private final HtmlGenerator htmlGenerator = new HtmlGenerator();
    
    @Test
    public void givenEmptyStyleOptionAddDefaultStyle() throws DocumentGeneratorException {
        // for pdf generator
        Document document = Document.builder().build();
        pdfGenerator.generate(document);
        assertFalse(document.getStyles().isEmpty());
        assertEquals(Input.DEFAULT, document.getStyles().get(0));
        // for html generator
        document = Document.builder().build();
        htmlGenerator.generate(document);
        assertFalse(document.getStyles().isEmpty());
        assertEquals(Input.DEFAULT, document.getStyles().get(0));
    }
    
}
