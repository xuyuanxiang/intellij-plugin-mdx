package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXFile;
import com.intellij.lang.*;
import com.intellij.lang.javascript.JSElementTypes;
import com.intellij.lang.javascript.psi.e4x.impl.JSXmlLiteralExpressionImpl;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.plugins.markdown.lang.MarkdownLanguage;
import org.jetbrains.annotations.NotNull;

public class MDXParserDefinition implements ParserDefinition {
    private static final IFileElementType FILE = new IStubFileElementType("MDX File", MDXLanguage.INSTANCE);

    @NotNull
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MDXFile(viewProvider);
    }

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new MDXLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(MarkdownLanguage.class)).createParser(project);
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(MarkdownLanguage.class)).getWhitespaceTokens();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(MarkdownLanguage.class)).getCommentTokens();
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(MarkdownLanguage.class)).spaceExistenceTypeBetweenTokens(left, right);
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return LanguageParserDefinitions.INSTANCE.forLanguage(Language.findInstance(MarkdownLanguage.class)).getStringLiteralElements();
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        final IElementType type = node.getElementType();
        if (type == JSElementTypes.JSX_XML_LITERAL_EXPRESSION) {
            return new JSXmlLiteralExpressionImpl(type);
        } else {
            throw new IllegalArgumentException("Unknown element: " + node);
        }
    }
}
