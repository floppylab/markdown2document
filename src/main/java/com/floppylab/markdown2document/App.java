package com.floppylab.markdown2document;

import com.floppylab.markdown2document.util.CommandLineTool;
import lombok.extern.java.Log;

@Log
public class App {

    /**
     * Main entry point.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new CommandLineTool().processCommandLineArgs(args);
    }


}
