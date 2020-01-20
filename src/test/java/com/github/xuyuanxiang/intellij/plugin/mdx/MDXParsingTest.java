package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.testFramework.ParsingTestCase;

public class MDXParsingTest extends ParsingTestCase {
    protected MDXParsingTest() {
        super("", "mdx", new MDXParserDefinition());
    }

    public void testParsingTestData() {
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
