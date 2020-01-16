package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class MDXLexerAdapter extends FlexAdapter {
    public MDXLexerAdapter() {
        super(new MDXLexer((Reader) null));
    }
}
