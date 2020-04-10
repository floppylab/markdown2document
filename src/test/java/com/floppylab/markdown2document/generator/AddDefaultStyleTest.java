/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floppylab.markdown2document.generator;

import java.io.IOException;
import java.net.URISyntaxException;
import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Input;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author bravemaster619
 */
public class AddDefaultStyleTest {
    
    private final PdfGenerator pdfGenerator = new PdfGenerator();
    private final HtmlGenerator htmlGenerator = new HtmlGenerator();
    
    @Test
    public void givenEmptyStyleOptionAddDefaultStyle() throws DocumentGeneratorException, IOException, URISyntaxException {
        // for pdf generator
        Document document = Document.builder().build();
        pdfGenerator.generate(document);
        assertFalse(document.getStyles().isEmpty());
        assertEquals(Input.getDefault().getContent(), document.getStyles().get(0).getContent());
        // for html generator
        document = Document.builder().build();
        htmlGenerator.generate(document);
        assertFalse(document.getStyles().isEmpty());
        assertEquals(Input.getDefault().getContent(), document.getStyles().get(0).getContent());
    }
    
}
