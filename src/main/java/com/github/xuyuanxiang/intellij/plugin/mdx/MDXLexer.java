package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.javascript.DialectOptionHolder;
import com.intellij.lang.javascript.JSFlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.MergeFunction;
import com.intellij.lexer.MergingLexerAdapterBase;
import com.intellij.psi.tree.IElementType;
import org.intellij.plugins.markdown.lang.lexer.MarkdownToplevelLexer;

public class MDXLexer extends MergingLexerAdapterBase {
    private final MarkdownToplevelLexer markdownToplevelLexer = new MarkdownToplevelLexer();

    public MDXLexer() {
        super(new JSFlexAdapter(DialectOptionHolder.JSX, true));
    }

    @Override
    public MergeFunction getMergeFunction() {
        return new MergeFunction() {
            @Override
            public IElementType merge(IElementType type, Lexer originalLexer) {
                markdownToplevelLexer.start(getTokenSequence(), getTokenStart(), getTokenEnd(), getState());
                return null;
            }
        };
    }
}
