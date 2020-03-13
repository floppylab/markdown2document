package com.floppylab.markdown2documentation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Getter
@ToString
@EqualsAndHashCode
public class InputFile {

    private String content;

    public InputFile(String content) {
        this.content = content;
    }

    public InputFile(URL url) throws IOException {
        this(url.openStream());
    }

    public InputFile(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
            sb.append("\n");
        }
        this.content = sb.toString();
    }

}
