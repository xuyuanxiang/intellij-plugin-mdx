package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.github.xuyuanxiang.intellij.plugin.mdx.parser.MDXParser;
import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXFile;
import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class MDXParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(MDXTypes.COMMENT);
    private static final IFileElementType FILE = new IFileElementType(MDXLanguage.INSTANCE);

    @NotNull
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MDXFile(viewProvider);
    }

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new MDXLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new MDXParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return MDXTypes.Factory.createElement(node);
    }
}
