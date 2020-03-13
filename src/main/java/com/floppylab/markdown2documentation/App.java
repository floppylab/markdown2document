package com.floppylab.markdown2documentation;

import lombok.extern.java.Log;

import java.util.Arrays;

@Log
public class App {

    /**
     * Main entry point.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        try {
            OutputFile outputFile = PdfGenerator.generatePdf(
                    Arrays.asList(new InputFile("# Sample header \n sample content ")),
                    Arrays.asList(new InputFile("body { font-family: sans-serif; color: #555; }")));
            outputFile.toFile("sample.pdf");
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }
}
