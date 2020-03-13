package com.floppylab.markdown2documentation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Getter
@ToString
@EqualsAndHashCode
public class OutputFile {

    private ByteArrayOutputStream content;

    public OutputFile(ByteArrayOutputStream content) {
        this.content = content;
    }

    /**
     * Returns the content as an OutputStream.
     *
     * @return output stream
     */
    public OutputStream toOutputStream() {
        return content;
    }

    /**
     * Saves the content as a file.
     *
     * @param name name of the file
     * @throws IOException if a low-level I/O problem occurs
     */
    public void toFile(String name) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(name)) {
            content.writeTo(outputStream);
        }
    }

}
