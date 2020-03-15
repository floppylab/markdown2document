package com.floppylab.markdown2document.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Getter
@EqualsAndHashCode
public class Output {

    private byte[] content;

    public Output(byte[] content) {
        this.content = content;
    }

    /**
     * Returns the content as a string.
     *
     * @return content string
     */
    public String toString() {
        return new String(content);
    }

    /**
     * Returns the content as an OutputStream.
     *
     * @return output stream
     * @throws IOException if a low-level I/O problem occurs
     */
    public OutputStream toOutputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(content);
        return byteArrayOutputStream;
    }

    /**
     * Saves the content as a file.
     *
     * @param name name of the file
     * @throws IOException if a low-level I/O problem occurs
     */
    public void toFile(String name) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(name)) {
            outputStream.write(content);
        }
    }

}
