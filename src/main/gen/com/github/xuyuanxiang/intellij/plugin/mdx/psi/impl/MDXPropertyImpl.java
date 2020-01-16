// This is a generated file. Not intended for manual editing.
package com.github.xuyuanxiang.intellij.plugin.mdx.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.xuyuanxiang.intellij.plugin.mdx.psi.MDXTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.xuyuanxiang.intellij.plugin.mdx.psi.*;

public class MDXPropertyImpl extends ASTWrapperPsiElement implements MDXProperty {

  public MDXPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MDXVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MDXVisitor) accept((MDXVisitor)visitor);
    else super.accept(visitor);
  }

}
