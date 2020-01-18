package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.github.xuyuanxiang.intellij.plugin.mdx.MDXLanguage;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.javascript.JavascriptLanguage;
import com.intellij.lang.javascript.dialects.JSXHarmonyLanguageDialect;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.intellij.plugins.markdown.lang.MarkdownLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.Set;

public class MDXFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider {
    public MDXFileViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, boolean eventSystemEnabled) {
        super(manager, virtualFile, eventSystemEnabled);
    }

    @NotNull
    @Override
    public Language getBaseLanguage() {
        return MDXLanguage.INSTANCE;
    }

    @NotNull
    @Override
    public Set<Language> getLanguages() {
        LinkedHashSet<Language> languages = new LinkedHashSet<>();
        languages.add(MDXLanguage.INSTANCE);
        languages.add(MarkdownLanguage.INSTANCE);
//        languages.add(JavascriptLanguage.INSTANCE);
        languages.add(Language.findInstance(JSXHarmonyLanguageDialect.class));
        return languages;
    }

    @Nullable
    @Override
    protected PsiFile createFile(@NotNull Language lang) {
        if (lang == MDXLanguage.INSTANCE) {
            return new MDXFile(this);
        }
        return LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
    }

    @NotNull
    @Override
    protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(@NotNull VirtualFile fileCopy) {
        return new MDXFileViewProvider(getManager(), fileCopy, false);
    }
}
