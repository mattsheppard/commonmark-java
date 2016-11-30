package org.commonmark.test;

import static org.junit.Assert.assertEquals;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.junit.Test;

public class CommonMarkRendererTest {

    @Test
    public void textContentEmphasis() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo\n***foo***\nbar\n\n***bar***"));
        assertEquals("foo\n***foo***\nbar\n\n***bar***", rendered);
    }

    @Test
    public void textContentQuotes() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo\n>foo\nbar\n\nbar"));
        assertEquals("foo\n>foo\nbar\n\nbar", rendered);
    }

    @Test
    public void textContentLinks() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo [text](http://link \"title\") bar"));
        assertEquals("foo [text](http://link \"title\") bar", rendered);

        rendered = defaultRenderer().render(parse("foo [text](http://link) bar"));
        assertEquals("foo [text](http://link) bar", rendered);

        rendered = defaultRenderer().render(parse("foo [text]() bar"));
        assertEquals("foo [text]() bar", rendered);

        rendered = defaultRenderer().render(parse("foo http://link bar"));
        assertEquals("foo http://link bar", rendered);
    }

    @Test
    public void textContentImages() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo ![text](http://link \"title\") bar"));
        assertEquals("foo ![text](http://link \"title\") bar", rendered);

        rendered = defaultRenderer().render(parse("foo ![text](http://link) bar"));
        assertEquals("foo ![text](http://link) bar", rendered);

        rendered = defaultRenderer().render(parse("foo ![text]() bar"));
        assertEquals("foo ![text]() bar", rendered);
    }

    @Test
    public void textContentLists() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo\n* foo\n* bar\n\nbar"));
        assertEquals("foo\n* foo\n* bar\n\nbar", rendered);

        rendered = defaultRenderer().render(parse("foo\n- foo\n- bar\n\nbar"));
        assertEquals("foo\n- foo\n- bar\n\nbar", rendered);

        rendered = defaultRenderer().render(parse("foo\n1. foo\n2. bar\n\nbar"));
        assertEquals("foo\n1. foo\n2. bar\n\nbar", rendered);

        rendered = defaultRenderer().render(parse("foo\n0) foo\n1) bar\n\nbar"));
        assertEquals("foo\n0) foo\n1) bar\n\nbar", rendered);
    }

    @Test
    public void textContentCode() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo `code` bar"));
        assertEquals("foo `code` bar", rendered);
    }

    @Test
    public void textContentCodeBlock() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo\n```\nfoo\nbar\n```\nbar"));
        assertEquals("foo\n```\nfoo\nbar\n```\nbar", rendered);

        rendered = defaultRenderer().render(parse("foo\n\n    foo\n     bar\nbar"));
        assertEquals("foo\n\n    foo\n     bar\nbar", rendered);
    }

    @Test
    public void textContentBrakes() {
        String rendered;

        rendered = defaultRenderer().render(parse("foo\nbar"));
        assertEquals("foo\nbar", rendered);

        rendered = defaultRenderer().render(parse("foo  \nbar"));
        assertEquals("foo  \nbar", rendered);

        rendered = defaultRenderer().render(parse("foo\n___\nbar"));
        assertEquals("foo\n___\nbar", rendered);
    }

    @Test
    public void textContentHtml() {
        String roundtripped;

        String html = "<table>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "           foobar\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>";
        roundtripped = defaultRenderer().render(parse(html));
        assertEquals(html, roundtripped);

        html = "foo <foo>foobar</foo> bar";
        roundtripped = defaultRenderer().render(parse(html));
        assertEquals(html, roundtripped);
    }

    private TextContentRenderer defaultRenderer() {
        return TextContentRenderer.builder().build();
    }

    private Node parse(String source) {
        return Parser.builder().build().parse(source);
    }
}
