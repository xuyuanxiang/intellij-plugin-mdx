package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.ASTFactory;
import com.intellij.lang.javascript.JSElementTypes;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MDXASTFactory extends ASTFactory {

    @Nullable
    @Override
    public CompositeElement createComposite(@NotNull IElementType type) {
        if (type == JSElementTypes.JSX_XML_LITERAL_EXPRESSION) {
            throw new IllegalArgumentException("JSX:" + type);
        }
        return super.createComposite(type);
    }

    @Nullable
    @Override
    public LeafElement createLeaf(@NotNull IElementType type, @NotNull CharSequence text) {
        return super.createLeaf(type, text);
    }
}
