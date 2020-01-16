package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.javascript.dialects.JSXHarmonyLanguageDialect;
import com.intellij.lang.javascript.psi.JSElementVisitor;
import com.intellij.lang.javascript.psi.JSFile;
import com.intellij.lang.javascript.psi.impl.JSFileImpl;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElementVisitor;
import org.intellij.plugins.markdown.lang.psi.MarkdownElementVisitor;
import org.intellij.plugins.markdown.lang.psi.impl.MarkdownFile;
import org.jetbrains.annotations.NotNull;

public class MDXFile extends PsiFileBase {
    private final MarkdownFile markdownFile;
    private final JSFile jsFile;

    public MDXFile(FileViewProvider viewProvider) {
        super(viewProvider, MDXLanguage.INSTANCE);
        markdownFile = new MarkdownFile(viewProvider);
        jsFile = new JSFileImpl(viewProvider, new JSXHarmonyLanguageDialect());
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof MarkdownElementVisitor) {
            ((MarkdownElementVisitor) visitor).visitMarkdownFile(markdownFile);
            return;
        }

        if (visitor instanceof JSElementVisitor) {
            ((JSElementVisitor) visitor).visitJSFile(jsFile);
        }

        visitor.visitFile(this);
    }

    @Override
    @NotNull
    public FileType getFileType() {
        return MDXFileType.INSTANCE;
    }
}
