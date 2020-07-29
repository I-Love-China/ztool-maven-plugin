package com.aliyun.code.typist.components.extractor;

import com.aliyun.code.typist.components.content.Comment;

import java.io.File;
import java.util.List;

public interface CommentExtractor {
    public boolean canExtract(File sourceFile);

    public List<Comment> extractComments(File sourceFile);
}
