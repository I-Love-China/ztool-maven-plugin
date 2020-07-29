package com.aliyun.code.typist.components.extractor;

import com.aliyun.code.typist.Util;

import java.io.File;

public class JavaCommentExtractor extends AbstractCommentExtractor {

    @Override
    public boolean canExtract(File srcFile) {
        return Util.isJavaFile(srcFile);
    }
}
