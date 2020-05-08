package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@Getter
@ToString(callSuper = true)
public class Content extends Input {

    public Content(String content) {
        super(content);
    }

}