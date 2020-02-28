package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.ecmascript6.ES6ElementTypes;
import com.intellij.lang.javascript.JSElementTypes;
import com.intellij.lang.javascript.ecmascript6.parsing.jsx.JSXParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.intellij.plugins.markdown.lang.parser.MarkdownParserManager;
import org.intellij.plugins.markdown.lang.parser.PsiBuilderFillingVisitor;
import org.jetbrains.annotations.NotNull;

public class MDXParser implements PsiParser {
    private static final Logger LOG = Logger.getInstance("MDX.Parser");

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        PsiBuilder.Marker rootMark = builder.mark();
        while (!builder.eof()) {
//            PsiBuilder.Marker marker = builder.mark();
            final IElementType type = builder.getTokenType();
//            LOG.debug("parse: tokenType=" + type + ", tokenText=" + builder.getTokenText());
//            if (type == MDXTokenTypes.IMPORT) {
//                marker.done(ES6ElementTypes.IMPORT_DECLARATION);
//            } else {
//                marker.done(root);
//            }
            builder.advanceLexer();
        }
        rootMark.done(root);
        return builder.getTreeBuilt();
    }
}
