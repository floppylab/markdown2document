package com.floppylab.markdown2document.domain;

import lombok.Getter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class Content extends Input {

    public Content(String content) {
        this.content = content;
    }

    public Content(URL url) throws IOException {
        this(url.openStream());
    }

    public Content(Path path) throws IOException {
        this(path, StandardCharsets.UTF_8);
    }

    public Content(Path path, Charset charSet) throws IOException {
        List<String> lines = Files.readAllLines(path, charSet);
        this.content = String.join("\n", lines);
    }

    public Content(InputStream inputStream) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                StringBuilder sb = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(str);
                }
                this.content = sb.toString();
            }
        }
    }

}
