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
import java.util.stream.Collectors;

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
        this.content = lines.stream().collect(Collectors.joining("\n"));
    }

    public Content(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
            sb.append("\n");
        }
        this.content = sb.toString();
    }

}
