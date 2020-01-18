package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.github.xuyuanxiang.intellij.plugin.mdx.MDXLanguage;
import com.intellij.lang.*;
import com.intellij.lang.javascript.dialects.JSXHarmonyLanguageDialect;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.ILazyParseableElementType;
import org.jetbrains.annotations.NotNull;

public interface MDXElementType {
    ILazyParseableElementType JS_OR_PARAGRAPH = new ILazyParseableElementType("JS_OR_PARAGRAPH", MDXLanguage.INSTANCE) {
        public ASTNode parseContents(@NotNull ASTNode chameleon) {
            PsiElement psi = chameleon.getPsi();
            assert psi != null : chameleon;
            Project project = psi.getProject();
            PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon);
            PsiParser parser = LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(JSXHarmonyLanguageDialect.class)).createParser(project);
            ASTNode result = parser.parse(this, builder).getFirstChildNode();
            return result;
        }
    };
    ILazyParseableElementType JSX_OR_HTML_BLOCK = new ILazyParseableElementType("JSX_OR_HTML_BLOCK", MDXLanguage.INSTANCE) {
        public ASTNode parseContents(@NotNull ASTNode chameleon) {
            PsiElement psi = chameleon.getPsi();
            assert psi != null : chameleon;
            Project project = psi.getProject();
            PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon);
            PsiParser parser = LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(JSXHarmonyLanguageDialect.class)).createParser(project);
            ASTNode result = parser.parse(this, builder).getFirstChildNode();
            return result;
        }
    };
}
