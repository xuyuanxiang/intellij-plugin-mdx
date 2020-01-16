package com.github.xuyuanxiang.intellij.plugin.mdx;

import com.intellij.lang.Language;

public class MDXLanguage extends Language {
    public static final MDXLanguage INSTANCE = new MDXLanguage();
    protected MDXLanguage() {
        // https://github.com/jshttp/mime-db/pull/160/commits/9dee875677799e8e24a2206eb83d7fdf698b4fca
        super("MDX", "text/mdx");
    }
}
