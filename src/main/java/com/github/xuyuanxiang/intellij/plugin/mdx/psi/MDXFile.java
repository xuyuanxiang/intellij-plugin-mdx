package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.github.xuyuanxiang.intellij.plugin.mdx.MDXFileType;
import com.github.xuyuanxiang.intellij.plugin.mdx.MDXLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class MDXFile extends PsiFileBase {

    public MDXFile(FileViewProvider viewProvider) {
        super(viewProvider, MDXLanguage.INSTANCE);
    }

    @Override
    @NotNull
    public FileType getFileType() {
        return MDXFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "MDX File";
    }
}
