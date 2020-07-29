package com.aliyun.code.typist.components.extractor;

import com.aliyun.code.typist.components.content.Comment;
import com.aliyun.code.typist.components.content.TxtContent;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractCommentExtractor implements CommentExtractor {
    private static final Pattern COMMENT_PATTERN;

    static {
        String slComment = "//[^\r\n]*";
        String mlComment = "/\\*[\\s\\S]*?\\*/";
        String strLit = "\"(?:\\\\.|[^\\\\\"\r\n])*\"";
        String chLit = "'(?:\\\\.|[^\\\\'\r\n])+'";
        String any = "[\\s\\S]";

        COMMENT_PATTERN = Pattern.compile(
                String.format("(%s)|(%s)|%s|%s|%s", slComment, mlComment, strLit, chLit, any)
        );
    }

    public boolean canExtract(File sourceFile) {
        return false;
    }

    @SneakyThrows
    public List<Comment> extractComments(File srcFile) {
        if (null == srcFile || !srcFile.exists()) {
            return Collections.emptyList();
        }

        TxtContent txtContent = new TxtContent(srcFile);

        List<Comment> comments = new ArrayList<>();
        Matcher m = COMMENT_PATTERN.matcher(txtContent.getContent());
        while (m.find()) {
            String hit = m.group();

            if (m.group(1) != null) {
                int lineNo = txtContent.getLineNoByOffset(m.start());
                comments.add(new Comment(lineNo, m.start(), Collections.singletonList(hit), srcFile));
            }
            if (m.group(2) != null) {
                int lineNo = txtContent.getLineNoByOffset(m.start());
                comments.add(new Comment(lineNo, m.start(), Arrays.<String>asList(hit.split("\n")), srcFile));
            }
        }

        return comments;
    }


}
