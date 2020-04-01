package com.floppylab.markdown2document.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ContentTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void givenStringCreateContentInstance() {
        final String contentString = "# Title";
        final Content content = new Content(contentString);
        assertEquals(contentString, content.getContent());
    }

    @Test
    public void givenPathCreateContentInstance() throws IOException {
        final File tempFile = folder.newFile("example.md");
        final Path path = Paths.get(tempFile.toURI());
        final String contentString = "# Title\n## Subtitle";
        Files.write(path, contentString.getBytes());

        final Content content = new Content(path);
        assertEquals(contentString, content.getContent());
    }

    @Test
    public void givenInputStreamCreateContentInstance() throws IOException {
        final String contentString = "# Title\n## Subtitle";
        try (final InputStream inputStream = new ByteArrayInputStream(contentString.getBytes())) {
            final Content content = new Content(inputStream);
            assertEquals(contentString, content.getContent());
        }
    }

}