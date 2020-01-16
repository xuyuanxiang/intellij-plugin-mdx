package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lexer.LayeredLexer;

public class MDXHighlightingLexer extends LayeredLexer {
    public MDXHighlightingLexer() {
        super(new MDXLexer());
    }
}
