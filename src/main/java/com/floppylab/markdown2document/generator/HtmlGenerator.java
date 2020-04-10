package com.floppylab.markdown2document.generator;

import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import com.floppylab.markdown2document.exception.DocumentIsNullException;
import com.floppylab.markdown2document.util.HtmlAssembler;
import lombok.extern.java.Log;

@Log
public class HtmlGenerator extends DocumentGenerator {

    @Override
    public Output generate(Document document) throws DocumentGeneratorException {

        if (document == null) {
            throw new DocumentIsNullException();
        }

        if (isStyleMissing(document)) {
            addDefaultStyle(document);
        }

        String content = HtmlAssembler.assemble(document);

        return new Output(content.getBytes());
    }


}
