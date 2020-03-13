package com.floppylab.markdown2documentation;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.extern.java.Log;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class PdfGenerator {

    private static List<Extension> extensions = Collections.singletonList(TablesExtension.create());

    /**
     * Generates a PDF file from the given markdown files.
     *
     * @param markdownFiles markdown files
     * @return output file
     * @throws Exception if a low-level I/O problem occurs
     */
    public static OutputFile generatePdf(List<InputFile> markdownFiles) throws Exception {
        return generatePdf(markdownFiles, new ArrayList<>());
    }

    /**
     * Generates a PDF file from the given markdown files.
     *
     * @param markdownFiles markdown files
     * @param styleFiles    style files
     * @return output file
     * @throws Exception if a low-level I/O problem occurs
     */
    public static OutputFile generatePdf(List<InputFile> markdownFiles, List<InputFile> styleFiles) throws Exception {

        String markdownContent = joinFileContents(markdownFiles);
        log.info(markdownContent);

        String styles = joinFileContents(styleFiles);
        log.info(styles);

        Node parsedDocument = parseDocument(markdownContent);

        String renderedDocument = renderDocument(parsedDocument, styles);
        log.info(renderedDocument);

        ByteArrayOutputStream byteArrayOutputStream = renderPdf(renderedDocument);
        return new OutputFile(byteArrayOutputStream);
    }

    private static String joinFileContents(List<InputFile> files) {
        return files
                .stream()
                .map(InputFile::getContent)
                .collect(Collectors.joining("\n"));
    }

    private static Node parseDocument(String markdownContent) {
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        return parser.parse(markdownContent);
    }

    private static String renderDocument(Node parsedDocument, String styles) {
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .softbreak("<br/>")
                .build();
        String renderedDocument = renderer.render(parsedDocument);
        renderedDocument = "<html>"
                + "<head>"
                + (styles != null && !styles.equals("") ? "<style>" + styles + "</style>" : "")
                + "</head>"
                + "<body>"
                + renderedDocument
                + "</body>"
                + "</html>";
        return renderedDocument;
    }

    private static ByteArrayOutputStream renderPdf(String renderedDocument) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        // builder.withHtmlContent(renderedDocument, ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
        builder.withHtmlContent(renderedDocument, "");
        builder.toStream(byteArrayOutputStream);
        builder.run();
        return byteArrayOutputStream;
    }

}
