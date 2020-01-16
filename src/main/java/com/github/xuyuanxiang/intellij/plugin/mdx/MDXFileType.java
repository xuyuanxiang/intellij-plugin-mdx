package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.ui.IconManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MDXFileType extends LanguageFileType {
    public static final MDXFileType INSTANCE = new MDXFileType();

    private static final Icon ICON = IconManager.getInstance().getIcon("/icons/MDX.svg", MDXFileType.class);

    private MDXFileType() {
        super(MDXLanguage.INSTANCE);
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ICON;
    }

    @NotNull
    @Override
    public String getName() {
        return "MDX";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "mdx";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "JSX in Markdown";
    }
}
