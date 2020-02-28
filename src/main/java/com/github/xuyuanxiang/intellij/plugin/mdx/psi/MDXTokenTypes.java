package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.github.xuyuanxiang.intellij.plugin.mdx.MDXLanguage;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;

public interface MDXTokenTypes {
    IFileElementType FILE = new IStubFileElementType("MDX:File", MDXLanguage.INSTANCE);
    IElementType IMPORT = new IElementType("MDX:IMPORT", MDXLanguage.INSTANCE);
    IElementType JSX = new IElementType("MDX:JSX", MDXLanguage.INSTANCE);
    IElementType JSX_CHILDREN = new IElementType("MDX:JSX_CHILDREN", MDXLanguage.INSTANCE);
    IElementType JSX_CLOSING = new IElementType("MDX:JSX_CLOSING", MDXLanguage.INSTANCE);
}
