package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.IElementType;
import org.intellij.plugins.markdown.lang.psi.MarkdownASTFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MDXASTFactory extends MarkdownASTFactory {
    private static final Logger LOG = Logger.getInstance("MDX.ASTFactory");

    @Nullable
    @Override
    public CompositeElement createComposite(@NotNull IElementType type) {
        LOG.debug("createComposite: " + type);
        return super.createComposite(type);
    }
}
