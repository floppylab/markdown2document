package com.floppylab.markdown2document.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentTest {

    @Test
    public void givenStringCreateContentInstance() {
        final String contentString = "# Title";
        final Content content = new Content(contentString);
        assertEquals(contentString, content.getContent());
    }

}