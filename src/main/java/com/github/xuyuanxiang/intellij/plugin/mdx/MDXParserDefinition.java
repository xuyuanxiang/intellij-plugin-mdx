package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;
import com.intellij.lang.jsx.JSXHarmonyParserDefinition;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;
import org.intellij.plugins.markdown.lang.MarkdownTokenTypeSets;
import org.intellij.plugins.markdown.lang.psi.impl.*;
import org.intellij.plugins.markdown.lang.stubs.MarkdownStubElementType;
import org.jetbrains.annotations.NotNull;

public class MDXParserDefinition extends JSXHarmonyParserDefinition {
    private static final IFileElementType MDX_FILE_ELEMENT_TYPE = new IStubFileElementType("MDX file", MDXLanguage.INSTANCE);

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
        final IElementType elementType = node.getElementType();
        if (elementType instanceof MarkdownStubElementType) {
            return ((MarkdownStubElementType) elementType).createElement(node);
        }
        if (elementType == MarkdownElementTypes.PARAGRAPH) {
            return new MarkdownParagraphImpl(node);
        }
        if (MarkdownTokenTypeSets.HEADERS.contains(elementType)) {
            return new MarkdownHeaderImpl(node);
        }
        if (elementType == MarkdownElementTypes.CODE_FENCE) {
            return ((MarkdownCodeFenceImpl) node);
        }
        if (MarkdownTokenTypeSets.LISTS.contains(elementType)) {
            return new MarkdownListImpl(node);
        }
        if (elementType == MarkdownElementTypes.LIST_ITEM) {
            return new MarkdownListItemImpl(node);
        }
        if (elementType == MarkdownElementTypes.BLOCK_QUOTE) {
            return new MarkdownBlockQuoteImpl(node);
        }
        if (elementType == MarkdownElementTypes.LINK_DEFINITION) {
            return new MarkdownLinkDefinitionImpl(node);
        }
        if (elementType == MarkdownElementTypes.LINK_DESTINATION) {
            return new MarkdownLinkDestinationImpl(node);
        }
        if (elementType == MarkdownElementTypes.CODE_BLOCK) {
            return new MarkdownCodeBlockImpl(node);
        }
        if (elementType == MarkdownElementTypes.TABLE) {
            return new MarkdownTableImpl(node);
        }
        if (elementType == MarkdownElementTypes.TABLE_ROW || elementType == MarkdownElementTypes.TABLE_HEADER) {
            return new MarkdownTableRowImpl(node);
        }
        if (elementType == MarkdownElementTypes.TABLE_CELL) {
            return new MarkdownTableCellImpl(node);
        }

        return super.createElement(node);
    }

    @NotNull
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MDXFile(viewProvider);
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return MDX_FILE_ELEMENT_TYPE;
    }
}
