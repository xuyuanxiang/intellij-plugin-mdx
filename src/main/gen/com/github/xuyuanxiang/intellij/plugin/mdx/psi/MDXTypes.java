// This is a generated file. Not intended for manual editing.
package com.github.xuyuanxiang.intellij.plugin.mdx.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.xuyuanxiang.intellij.plugin.mdx.psi.impl.*;

public interface MDXTypes {

  IElementType PROPERTY = new MDXElementType("PROPERTY");

  IElementType COMMENT = new MDXTokenType("COMMENT");
  IElementType CRLF = new MDXTokenType("CRLF");
  IElementType KEY = new MDXTokenType("KEY");
  IElementType SEPARATOR = new MDXTokenType("SEPARATOR");
  IElementType VALUE = new MDXTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new MDXPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
