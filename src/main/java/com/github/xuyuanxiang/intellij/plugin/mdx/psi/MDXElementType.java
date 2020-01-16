package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.github.xuyuanxiang.intellij.plugin.mdx.MDXLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MDXElementType extends IElementType {
    public MDXElementType(@NotNull @NonNls String debugName) {
        super(debugName, MDXLanguage.INSTANCE);
    }
}
