package com.aliyun.code.typist.components.extractor;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class ExtractorFactory {
    private static final CommentExtractor JAVA_COMMENT_EXTRACTOR = new JavaCommentExtractor();

    public static CommentExtractor getCommentExtractor(File srcFile) {
        if (null == srcFile || !srcFile.exists() || !srcFile.isFile()) {
            throw new IllegalArgumentException();
        }


        String ext = FilenameUtils.getExtension(srcFile.getAbsolutePath());
        switch (ext) {
            case "java":
                return JAVA_COMMENT_EXTRACTOR;
        }
        return null;
    }
}
