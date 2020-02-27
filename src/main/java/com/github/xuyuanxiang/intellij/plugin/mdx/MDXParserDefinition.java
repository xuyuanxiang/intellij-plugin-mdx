package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXFile;
import com.intellij.lang.*;
import com.intellij.lang.jsx.JSXHarmonyParserDefinition;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import org.intellij.plugins.markdown.lang.parser.MarkdownParserDefinition;
import org.intellij.plugins.markdown.lang.parser.MarkdownParserManager;
import org.intellij.plugins.markdown.lang.parser.PsiBuilderFillingVisitor;
import org.jetbrains.annotations.NotNull;

public class MDXParserDefinition extends MarkdownParserDefinition {
    private static final Logger LOG = Logger.getInstance("MDX.ParserDefinition");
    private static final IFileElementType FILE = new IStubFileElementType("MDX File", MDXLanguage.INSTANCE);

    @NotNull
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MDXFile(viewProvider);
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiParser createParser(Project project) {
        return (root, builder) -> {
            PsiBuilder.Marker rootMarker = builder.mark();

            final org.intellij.markdown.ast.ASTNode parsedTree = MarkdownParserManager.parseContent(builder.getOriginalText(), MarkdownParserManager.FLAVOUR);

            assert builder.getCurrentOffset() == 0;
            new MDXVisitor(builder).visitNode(parsedTree);
            assert builder.eof();

            rootMarker.done(root);

            return builder.getTreeBuilt();
        };
    }
}
