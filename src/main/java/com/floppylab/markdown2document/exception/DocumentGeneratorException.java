package com.floppylab.markdown2document.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentGeneratorException extends Exception {

    private String message;

}
