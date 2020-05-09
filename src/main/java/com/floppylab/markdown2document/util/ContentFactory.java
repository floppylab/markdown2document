package com.floppylab.markdown2document.util;

import com.floppylab.markdown2document.domain.Content;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContentFactory {

    private static ContentFactory INSTANCE;

    static {
        INSTANCE = new ContentFactory();
    }

    public static ContentFactory getInstance() {
        return INSTANCE;
    }

    public Content create(final String content) {
        return new Content(content);
    }

    public Content create(URL url) throws IOException {
        return create(url.openStream());
    }

    public Content create(Path path) throws IOException {
        return create(path, StandardCharsets.UTF_8);
    }

    public Content create(Path path, Charset charSet) throws IOException {
        return create(String.join("\n", Files.readAllLines(path, charSet)));
    }

    public Content create(InputStream inputStream) throws IOException {
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
                return create(sb.toString());
            }
        }
    }

}
