package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Link extends Input {

    public Link(String link) {
        this.content = link;
    }

}
