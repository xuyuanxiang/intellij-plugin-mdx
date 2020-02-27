package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.javascript.JSElementTypes;
import com.intellij.lang.javascript.types.JSXmlAttributeElementType;
import com.intellij.openapi.diagnostic.Logger;
import org.intellij.markdown.IElementType;
import org.intellij.markdown.MarkdownElementTypes;
import org.intellij.markdown.ast.ASTNode;
import org.intellij.markdown.ast.LeafASTNode;
import org.intellij.markdown.ast.visitors.RecursiveVisitor;
import org.intellij.plugins.markdown.lang.MarkdownElementType;
import org.jetbrains.annotations.NotNull;

public class MDXVisitor extends RecursiveVisitor {
    private static final Logger LOG = Logger.getInstance("MDX.Visitor");
    @NotNull
    private final PsiBuilder builder;

    public MDXVisitor(@NotNull PsiBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void visitNode(@NotNull ASTNode node) {
        if (node instanceof LeafASTNode) {
            /* a hack for the link reference definitions:
             * they are being parsed independent from link references and
             * the link titles and urls are tokens instead of composite elements
             */
            final IElementType type = node.getType();
            if (type != MarkdownElementTypes.LINK_LABEL && type != MarkdownElementTypes.LINK_DESTINATION) {
                return;
            }
        }

        ensureBuilderInPosition(node.getStartOffset());
        final PsiBuilder.Marker marker = builder.mark();

        super.visitNode(node);

        ensureBuilderInPosition(node.getEndOffset());
        marker.done(MarkdownElementType.platformType(node.getType()));
    }

    private void ensureBuilderInPosition(int position) {
        while (builder.getCurrentOffset() < position) {
            builder.advanceLexer();
        }

        if (builder.getCurrentOffset() != position) {
            throw new AssertionError("parsed tree and lexer are unsynchronized");
        }
    }
}
