package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Getter
@ToString
public abstract class Input {

    protected String content;

    public static Content getDefault() throws IOException {
        InputStream stream = Content.class.getClassLoader().getResourceAsStream("resources/default.css");
        String content = IOUtils.toString(stream, StandardCharsets.UTF_8.toString());
        return new Content(content);
    }

}
