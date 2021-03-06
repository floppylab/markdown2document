[![Build Status](https://travis-ci.org/floppylab/markdown2document.svg?branch=master)](https://travis-ci.org/floppylab/markdown2document) 
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENCE)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=floppylab/markdown2document)](https://dependabot.com)

# markdown2document

Turn markdown files to a PDF or HTML document with the help of this java library. You can also use CSS styles to format your documents.

## 1. How to use

### 1.1. As a library

First, you have to assemble a _Document_ object. Then you use a _Generator_ to generate you document.

There are currently two generators in the library:
- `PdfGenerator`
- `HTMLGenerator`

Both have a `generate(Document document)` method which returns an `Output` object.

Here is an example how to use is as a library:

``` java
ContentFactory contentFactory = ContentFactory.getInstance();

Document document = Document
        .builder()
        .markdownContents(Arrays.asList(
                contentFactory.create("# Sample header \n sample content "),
                contentFactory.create(new URL("https://floppylab.com/resources/markdown2document/sample.md"))
        ))
        .styles(Arrays.asList(
                contentFactory.create("body { font-family: sans-serif; color: #555; /* some comment*/ }"),
                new Link("https://floppylab.com/resources/markdown2document/sample.css")
        )).build();

PdfGenerator pdfGenerator = new PdfGenerator();
Output output = pdfGenerator.generate(document);
output.toFile("sample.pdf");

HtmlGenerator htmlGenerator = new HtmlGenerator();
output = htmlGenerator.generate(document);
output.toFile("sample.html");
```

#### 1.1.1. _Document_

A document can consist of:
- a list of markdown contents - `List<Content> markdownContents`
- a list of style inputs - `List<Input> styleInputs`
- base uri for relative links (images, links, etc) - `String baseUri`

#### 1.1.2. _Input_

_Input_ is an abstract class and there are two classes that extend it:

- `Content` 
- `Link`

##### 1.1.2.1. _Content_

A `Content` object can be constructed directly from:
- `String` - as a text

But also can be constructed by `ContentFactory` from:
- `String` - as a text
- `URL` - url where the content can be found
- `Path` - path to file with optional `Charset`
- `InputStream` - stream with content

##### 1.1.2.2. _Link_

A `Link` object can be constructed from:
- `String` - link

#### 1.1.3. _Output_

An _Output_ object contains the contents of the generated document, and it has the following methods:
- `toString` - returns the content as a `String`
- `toOutputStream` - returns the content as an `OutputStream`
- `toFile(String name)` - writes the content into a file with the given name (`FileOutputStream` is used)

### 1.2. As a command line tool

```
java -jar markdown2document-1.0.0.jar -h

java -jar markdown2document-1.0.0.jar -mc "# title","*italic text*" -pf "sample.pdf"
```

You can use it as a command line tool with the following options:

#### Markdown options
- markdown contents
    - short: `mc`
    - long: `markdown-contents`
    - multiple values

- markdown urls
    - short: `mu`
    - long: `markdown-urls`
    - multiple values

- markdown file paths
    - short: `mp`
    - long: `markdown-paths`
    - multiple values

#### Styling options
- style links
    - short: `sl`
    - long: `style-links`
    - multiple values

- style contents
    - short: `sc`
    - long: `style-contents`
    - multiple values

- style urls
    - short: `su`
    - long: `style-urls`
    - multiple values

- style paths
    - short: `sp`
    - long: `style-paths`
    - multiple values

#### Base uri 
- base uri
    - short: `bu`
    - long: `base-uri`

#### Output options 
- to pdf file with filename
    - short: `pf`
    - long: `to-pdf`

- to html file with filename
    - short: `hf`
    - long: `to-html`

- to html string
    - short: `hs`
    - long: `to-html-string`

#### Help
- help
    - short: `h`
    - long: `help`


## 2. Credits

This library is based on a few other libraries so credit goes to the contributors of these projects.

### CommonMark 

The library uses CommonMark to parse the markdown input contents.

Learn more about it here: 
- [official CommonMark tutorial](https://commonmark.org/help/tutorial/)
- [CommonMark tutorial with samples](https://github.com/thephpleague/commonmark/blob/master/tests/benchmark/sample.md)

### commonmark-java

commonmark-java is a Java library for parsing and rendering Markdown text according to the CommonMark specification by Atlassian.

Check the project on [GitHub](https://github.com/atlassian/commonmark-java).

### openhtmltopdf

openhtmltopdf is an HTML to PDF library for the JVM.

Check the project on [GitHub](https://github.com/danfickle/openhtmltopdf).

## 3. Contribution

Please see [CONTRIBUTING.md](CONTRIBUTING.md) file for details.

## 4. Postcards
You're free to use this package, but if it makes it to your production environment we highly appreciate you sending us a postcard from your hometown, mentioning which of our project(s) you are using.

Our address is: Gedőci utca 25., 3100 Salgótarján, Hungary.

We publish all received postcards on our [website](https://floppylab.com).

## 5. License
The MIT License (MIT). Please see [LICENSE](LICENSE) file for more information.
