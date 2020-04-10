package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import com.floppylab.markdown2document.exception.DocumentIsNullException;
import com.floppylab.markdown2document.util.HtmlAssembler;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.extern.java.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Log
public class PdfGenerator extends DocumentGenerator {

    @Override
    public Output generate(Document document) throws DocumentGeneratorException {

        if (document == null) {
            throw new DocumentIsNullException();
        }
        if (isStyleMissing(document)) {
            addDefaultStyle(document);
        }

        String content = HtmlAssembler.assemble(document);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        String baseUri = document.getBaseUri() != null ? document.getBaseUri() : "";
        builder.withHtmlContent(content, baseUri);
        builder.toStream(byteArrayOutputStream);
        try {
            builder.run();
            return new Output(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new DocumentGeneratorException(e.getMessage());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException ex) {
                log.warning("Could not close the stream");
            }
        }
    }

}
