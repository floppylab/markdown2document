package com.floppylab.markdown2document;

import com.floppylab.markdown2document.domain.Content;
import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Link;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.generator.HtmlGenerator;
import com.floppylab.markdown2document.generator.PdfGenerator;
import lombok.extern.java.Log;

import java.net.URL;
import java.util.Arrays;

@Log
public class App {

    /**
     * Main entry point.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        try {
            Document document = Document
                    .builder()
                    .markdownContents(Arrays.asList(
                            new Content("# Sample header \n sample content "),
                            new Content(new URL("https://floppylab.com/projects/markdown2document/sample.md"))
                    ))
                    .styles(Arrays.asList(
                            new Content("body { font-family: sans-serif; color: #555; /* some comment*/ }"),
                            new Link("https://floppylab.com/projects/markdown2document/sample.css")
                    )).build();
            PdfGenerator pdfGenerator = new PdfGenerator();
            Output output = pdfGenerator.generate(document);
            output.toFile("sample.pdf");

            HtmlGenerator htmlGenerator = new HtmlGenerator();
            output = htmlGenerator.generate(document);
            output.toFile("sample.html");
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }
}
