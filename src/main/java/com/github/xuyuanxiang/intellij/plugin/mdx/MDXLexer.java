package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.javascript.DialectOptionHolder;
import com.intellij.lang.javascript.JSFlexAdapter;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.intellij.plugins.markdown.lang.lexer.MarkdownToplevelLexer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MDXLexer extends MarkdownToplevelLexer {
    private static final Logger LOG = Logger.getInstance("MDX.Lexer");
    private JSFlexAdapter jsx = new JSFlexAdapter(DialectOptionHolder.JSX);
    private IElementType myTokenType;
    private int myTokenStart;
    private int myTokenEnd;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.myTokenType = null;
        super.start(buffer, startOffset, endOffset, initialState);
        jsx.start(buffer, startOffset, endOffset, initialState);
    }

    @Override
    public void advance() {
        this.myTokenType = null;
        super.advance();
        jsx.advance();
    }


    @Nullable
    @Override
    public IElementType getTokenType() {
        if (this.myTokenType == null) {
            this.myTokenType = super.getTokenType();
            this.myTokenStart = super.getTokenStart();
            this.myTokenEnd = super.getTokenEnd();
            LOG.debug("getTokenType: " + jsx.getTokenType());
        }
        return this.myTokenType;
    }

    public int getTokenStart() {
        return this.myTokenType != null ? this.myTokenStart : super.getTokenStart();
    }

    public int getTokenEnd() {
        return this.myTokenType != null ? this.myTokenEnd : super.getTokenEnd();
    }
}
