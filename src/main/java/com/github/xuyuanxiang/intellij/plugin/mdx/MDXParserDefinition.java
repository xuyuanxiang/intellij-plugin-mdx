package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXFile;
import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.plugins.markdown.lang.psi.MarkdownPsiFactory;
import org.intellij.plugins.markdown.lang.stubs.MarkdownStubElementType;
import org.jetbrains.annotations.NotNull;

public class MDXParserDefinition implements ParserDefinition {
    private static final Logger LOG = Logger.getInstance("MDX.ParserDefinition");
    @NotNull
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MDXFile(viewProvider);
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return MDXTokenTypes.FILE;
    }

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new MDXLexer();
    }

    @NotNull
    @Override
    public PsiParser createParser(Project project) {
        return new MDXParser();
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        final IElementType type = node.getElementType();
        LOG.debug("createElement: " + type);
        return type instanceof MarkdownStubElementType
                ? ((MarkdownStubElementType)type).createElement(node)
                : MarkdownPsiFactory.INSTANCE.createElement(node);
    }

    @NotNull
    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return TokenSet.create();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }
}
