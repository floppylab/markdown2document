package com.floppylab.markdown2document.domain;

import java.io.IOException;
import java.net.URISyntaxException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Input {

    protected String content;

    public static Input getDefault() throws IOException, URISyntaxException {
        return Content.getDefault();
    }

}
