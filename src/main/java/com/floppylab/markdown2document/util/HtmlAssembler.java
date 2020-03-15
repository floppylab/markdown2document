package com.floppylab.markdown2document.util;

import com.floppylab.markdown2document.domain.Content;
import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Input;
import com.floppylab.markdown2document.domain.Link;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HtmlAssembler {

    public static List<Extension> EXTENSIONS = Collections.singletonList(TablesExtension.create());

    /**
     * Assembles HTML from markdown contents and styles.
     *
     * @param document document descriptor object
     * @return rendered HTML content
     */
    public static String assemble(Document document) {
        String markdownContent = joinFileContents(document.getMarkdownContents());

        Node parsedDocument = parseMarkdown(markdownContent);

        return renderDocument(parsedDocument, document.getStyles());
    }

    private static String joinFileContents(List<Content> files) {
        if (files == null) return "";
        return files
                .stream()
                .map(Content::getContent)
                .collect(Collectors.joining("\n"));
    }

    private static Node parseMarkdown(String markdownContent) {
        Parser parser = Parser.builder()
                .extensions(EXTENSIONS)
                .build();
        return parser.parse(markdownContent);
    }

    private static String renderDocument(Node parsedDocument, List<Input> styles) {
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(EXTENSIONS)
                .softbreak("<br/>")
                .build();

        String renderedDocument = renderer.render(parsedDocument);
        renderedDocument = "<html>"
                + "<head>"
                + formatStyles(styles)
                + "</head>"
                + "<body>"
                + renderedDocument
                + "</body>"
                + "</html>";
        return renderedDocument;
    }

    private static String formatStyles(List<Input> styles) {
        StringBuilder s = new StringBuilder();
        for (Input style : styles) {
            if (style instanceof Content) {
                s.append("<style>");
                s.append(style.getContent());
                s.append("</style>");
            } else if (style instanceof Link) {
                s.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
                s.append(style.getContent());
                s.append("\"></link>");
            }
        }
        return s.toString();
    }

}
