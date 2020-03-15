package com.floppylab.markdown2document.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Document {

    private List<Content> markdownContents;

    private List<Input> styles;

    private String baseUri;

}
