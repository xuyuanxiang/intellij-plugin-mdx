package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.github.xuyuanxiang.intellij.plugin.mdx.MDXLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MDXTokenType extends IElementType {
    public MDXTokenType(@NotNull @NonNls String debugName) {
        super(debugName, MDXLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "MDXTokenType." + super.toString();
    }
}
