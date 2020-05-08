package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Getter
@ToString
public class Input {

    protected String content;

    protected Input(String content) {
        this.content = content;
    }

    public static Input getDefault() throws IOException {
        InputStream stream = Content.class.getClassLoader().getResourceAsStream("resources/default.css");
        String content = IOUtils.toString(stream, StandardCharsets.UTF_8.toString());
        return new Input(content);
    }

}
