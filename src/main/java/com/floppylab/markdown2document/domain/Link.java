package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Link extends Input {
  
    public static final Link DEFAULT = new Link("https://floppylab.com/resources/markdown2document/sample.css");

    public Link(String link) {
        this.content = link;
    }

}
