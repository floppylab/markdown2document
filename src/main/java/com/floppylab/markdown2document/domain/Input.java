package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Input {

    public static Input DEFAULT = Link.DEFAULT;
    protected String content;

}
