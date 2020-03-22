package com.floppylab.markdown2document.util;

import com.floppylab.markdown2document.AppTest;
import com.floppylab.markdown2document.domain.Content;
import com.floppylab.markdown2document.domain.Document;
import lombok.extern.java.Log;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@Log
public class HtmlAssemblerTest {

    @Test
    public void givenEmptyDocumentThenEmptyHtmlReturned() {
        Document document = Document.builder().build();
        String assembledDocument = HtmlAssembler.assemble(document);
        assertEquals("<html><head></head><body></body></html>", assembledDocument);
    }

    @Test
    public void givenMarkdownInputHeaderThenHtmlReturned() {
        Document document = Document
                .builder()
                .markdownContents(Collections.singletonList(new Content("# title")))
                .build();
        String assembledDocument = HtmlAssembler.assemble(document);
        assertEquals("<html><head></head><body><h1>title</h1></body></html>", removeWhiteSpaces(assembledDocument));
    }

    public String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }
}
