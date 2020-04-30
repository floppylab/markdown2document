package com.floppylab.markdown2document.util;

import com.floppylab.markdown2document.domain.Content;
import com.floppylab.markdown2document.domain.Document;
import com.floppylab.markdown2document.domain.Link;
import com.floppylab.markdown2document.domain.Output;
import com.floppylab.markdown2document.exception.DocumentGeneratorException;
import com.floppylab.markdown2document.generator.DocumentGenerator;
import com.floppylab.markdown2document.generator.HtmlGenerator;
import com.floppylab.markdown2document.generator.PdfGenerator;
import lombok.extern.java.Log;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

@Log
public class CommandLineTool {

    /**
     * Process command line args and generate documents based on them.
     *
     * @param args args
     */
    public void processCommandLineArgs(String[] args) {

        if (args.length == 0) return;

        Options options = new Options();

        // markdown
        Option markdownContents = new Option("mc", "markdown-contents", true, "markdown contents");
        markdownContents.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(markdownContents);

        Option markdownUrls = new Option("mu", "markdown-urls", true, "markdown urls");
        markdownUrls.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(markdownUrls);

        Option markdownPaths = new Option("mp", "markdown-paths", true, "markdown file paths");
        markdownPaths.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(markdownPaths);

        // style
        Option styleLinks = new Option("sl", "style-links", true, "style links");
        styleLinks.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(styleLinks);

        Option styleContents = new Option("sc", "style-contents", true, "style contents");
        styleContents.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(styleContents);

        Option styleUrls = new Option("su", "style-urls", true, "style urls");
        styleUrls.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(styleUrls);

        Option stylePaths = new Option("sp", "style-paths", true, "style file paths");
        stylePaths.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(stylePaths);

        // base uri
        Option baseUri = new Option("bu", "base-uri", true, "base uri");
        options.addOption(baseUri);

        // output
        Option toPdf = new Option("pf", "to-pdf", true, "name of pdf file");
        options.addOption(toPdf);

        Option toHtmlFile = new Option("hf", "to-html-file", true, "name of html file");
        options.addOption(toHtmlFile);

        Option toHtmlString = new Option("hs", "to-html-string", false, "html output");
        options.addOption(toHtmlString);

        // help
        Option help = new Option("h", "help", false, "help");
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            Document document = Document
                    .builder()
                    .markdownContents(new ArrayList<>())
                    .styles(new ArrayList<>())
                    .build();
            DocumentGenerator documentGenerator = null;
            String fileName = null;

            // help
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("markdown2document", options);
                return;
            }

            // markdown
            if (cmd.hasOption("mc")) {
                String[] markdownContentsValue = cmd.getOptionValue("mc").split(",");
                for (String value : markdownContentsValue) {
                    document.getMarkdownContents().add(new Content(value));
                }
            }

            if (cmd.hasOption("mu")) {
                String[] markdownUrlsValue = cmd.getOptionValue("mu").split(",");
                for (String url : markdownUrlsValue) {
                    document.getMarkdownContents().add(ContentFactory.getInstance().create(new URL(url)));
                }
            }

            if (cmd.hasOption("mp")) {
                String[] markdownPathsValue = cmd.getOptionValue("mp").split(",");
                for (String path : markdownPathsValue) {
                    document.getMarkdownContents().add(ContentFactory.getInstance().create(Paths.get(path)));
                }
            }

            // style
            if (cmd.hasOption("sc")) {
                String[] styleContentsValue = cmd.getOptionValue("sc").split(",");
                for (String value : styleContentsValue) {
                    document.getStyles().add(ContentFactory.getInstance().create(value));
                }
            }

            if (cmd.hasOption("su")) {
                String[] styleUrlsValue = cmd.getOptionValue("su").split(",");
                for (String url : styleUrlsValue) {
                    document.getStyles().add(ContentFactory.getInstance().create(new URL(url)));
                }
            }

            if (cmd.hasOption("sp")) {
                String[] stylePathsValue = cmd.getOptionValue("sp").split(",");
                for (String path : stylePathsValue) {
                    document.getStyles().add(ContentFactory.getInstance().create(Paths.get(path)));
                }
            }

            if (cmd.hasOption("sl")) {
                String[] styleLinksValue = cmd.getOptionValue("sl").split(",");
                for (String link : styleLinksValue) {
                    document.getStyles().add(new Link(link));
                }
            }

            // base uri
            if (cmd.hasOption("bu")) {
                String baseUriValue = cmd.getOptionValue("bu");
                document.setBaseUri(baseUriValue);
            }

            // outcome
            if (cmd.hasOption("pf")) {
                documentGenerator = new PdfGenerator();
                fileName = cmd.getOptionValue("pf");
            }

            if (cmd.hasOption("hf")) {
                documentGenerator = new HtmlGenerator();
                fileName = cmd.getOptionValue("hf");
            }

            if (cmd.hasOption("hs")) {
                documentGenerator = new HtmlGenerator();
            }

            // processing
            log.info(document.toString());
            if (documentGenerator != null) {
                Output output = documentGenerator.generate(document);
                if (fileName != null) {
                    output.toFile(fileName);
                } else if (documentGenerator instanceof HtmlGenerator) {
                    log.info(output.toString());
                } else {
                    throw new DocumentGeneratorException();
                }
            } else {
                throw new DocumentGeneratorException();
            }

        } catch (ParseException | IOException | DocumentGeneratorException e) {
            log.info("Error happened while parsing arguments");
        }
    }
}
