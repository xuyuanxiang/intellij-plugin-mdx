package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXTokenTypes;
import com.intellij.lang.javascript.JSFlexAdapter;
import com.intellij.lang.javascript.JSTokenTypes;
import com.intellij.lang.javascript.dialects.JSXHarmonyLanguageDialect;
import com.intellij.lexer.LexerBase;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.intellij.plugins.markdown.lang.MarkdownTokenTypes;
import org.intellij.plugins.markdown.lang.lexer.MarkdownLexerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MDXLexer extends LexerBase {
    private static final Logger LOG = Logger.getInstance("MDX.Lexer");
    private final JSFlexAdapter jsx = new JSFlexAdapter(JSXHarmonyLanguageDialect.DIALECT_OPTION_HOLDER);
    private final MarkdownLexerAdapter md = new MarkdownLexerAdapter();
    private int tokenStart;
    private int tokenEnd;
    private int endOffset;
    private CharSequence originBuffer;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.endOffset = endOffset;
        this.originBuffer = buffer;
        this.tokenStart = startOffset;
        this.tokenEnd = endOffset;
        LOG.trace("start: buffer=" + buffer + ", startOffset=" + startOffset + ", endOffset=" + endOffset + ", initialState=" + initialState);
        jsx.start(buffer, startOffset, endOffset, initialState);
        md.start(buffer, startOffset, endOffset, initialState);
    }

    @Override
    public int getState() {
        LOG.trace("getState: jsx=" + jsx.getState() + ", md=" + md.getState());
        return 0;
    }

    @Nullable
    @Override
    public IElementType getTokenType() {
        final IElementType type = jsx.getTokenType();
        IElementType tokenType;
        if (type == JSTokenTypes.IMPORT_KEYWORD) {
            tokenType = MDXTokenTypes.IMPORT;
            this.tokenStart = jsx.getTokenStart();
            while (true) {
                if (jsx.getTokenType() == JSTokenTypes.SEMICOLON || md.getTokenType() == MarkdownTokenTypes.EOL) {
                    this.advance();
                    break;
                }
                LOG.debug("merge: MDX=" + tokenType + ", jsx=" + jsx.getTokenType() + ", markdown=" + md.getTokenType() + ", text=" + jsx.getTokenText());
                this.advance();
            }
            this.tokenEnd = jsx.getTokenEnd();
        } else if (type == JSTokenTypes.XML_START_TAG_START) {
            tokenType = MDXTokenTypes.JSX;
            this.tokenStart = jsx.getTokenStart();
            while (true) {
                if (jsx.getTokenType() == JSTokenTypes.XML_END_TAG_START) {
                    break;
                }
                if (jsx.getTokenType() == JSTokenTypes.XML_EMPTY_TAG_END) {
                    break;
                }
                LOG.debug("merge: MDX=" + tokenType + ", jsx=" + jsx.getTokenType() + ", markdown=" + md.getTokenType() + ", text=" + jsx.getTokenText());
                this.advance();
            }
            this.tokenEnd = jsx.getTokenEnd();
        } else {
            this.tokenStart = md.getTokenStart();
            this.tokenEnd = md.getTokenEnd();
            tokenType = md.getTokenType();
        }
        return tokenType;
    }

    @Override
    public int getTokenStart() {
//        LOG.debug("getTokenStart: " + this.tokenStart);
        return this.tokenStart;
    }

    @Override
    public int getTokenEnd() {
        LOG.debug("getTokenEnd: " + this.tokenEnd);
        return this.tokenEnd;
    }

    @Override
    public void advance() {
        LOG.debug("advance");
        jsx.advance();
        md.advance();
    }

    @NotNull
    @Override
    public CharSequence getBufferSequence() {
        LOG.debug("getBufferSequence: " + this.originBuffer);
        return this.originBuffer;
    }

    @Override
    public int getBufferEnd() {
        LOG.debug("getBufferEnd: " + this.endOffset);
        return this.endOffset;
    }

}
