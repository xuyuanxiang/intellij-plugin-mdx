package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.javascript.DialectOptionHolder;
import com.intellij.lang.javascript.dialects.ECMA6SyntaxHighlighterFactory;
import com.intellij.lang.javascript.highlighting.JSHighlighter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.apache.commons.lang.ArrayUtils;
import org.intellij.plugins.markdown.highlighting.MarkdownSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;

public class MDXHighlighter extends SyntaxHighlighterBase {
    private final JSHighlighter jsHighlighter = new ECMA6SyntaxHighlighterFactory.ECMA6SyntaxHighlighter(DialectOptionHolder.JSX, false);
    private final MarkdownSyntaxHighlighter markdownSyntaxHighlighter = new MarkdownSyntaxHighlighter();

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        jsHighlighter.getHighlightingLexer();
        markdownSyntaxHighlighter.getHighlightingLexer();
        return new MDXHighlightingLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return (TextAttributesKey[]) ArrayUtils.addAll(markdownSyntaxHighlighter.getTokenHighlights(tokenType), jsHighlighter.getTokenHighlights(tokenType));
    }
}
