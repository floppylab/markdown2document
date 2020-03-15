package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import com.floppylab.markdown2document.exception.DocumentIsNullException;
import com.floppylab.markdown2document.util.HtmlAssembler;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.extern.java.Log;

import java.io.ByteArrayOutputStream;

@Log
public class PdfGenerator extends DocumentGenerator {

    @Override
    public Output generate(Document document) throws DocumentGeneratorException {

        if (document == null) {
            throw new DocumentIsNullException();
        }

        String content = HtmlAssembler.assemble(document);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        String baseUri = document.getBaseUri() == null ? document.getBaseUri() : "";
        builder.withHtmlContent(content, baseUri);
        builder.toStream(byteArrayOutputStream);
        try {
            builder.run();
        } catch (Exception e) {
            throw new DocumentGeneratorException(e.getMessage());
        }
        return new Output(byteArrayOutputStream.toByteArray());
    }


}
