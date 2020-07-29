package com.aliyun.code.typist.components.checker;

import com.aliyun.code.typist.components.content.Comment;
import org.apache.maven.plugin.logging.Log;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 检查注释中的 T.O.D.O
 */
public class TodoChecker {
    private static final TodoChecker INSTANCE = new TodoChecker();

    public static TodoChecker getInstance() {
        return INSTANCE;
    }

    private static final Pattern TODO_PATTERN;

    static {
        TODO_PATTERN = Pattern.compile("[^a-z]*TODO[^a-z]*", Pattern.CASE_INSENSITIVE);
    }

    public void check(List<Comment> comments, Log log) {
        if (null == comments || comments.isEmpty()) {
            return;
        }

        for (Comment comment : comments) {
            int lineNo = 1;
            for (String line : comment.getLines()) {
                if (TODO_PATTERN.matcher(line).find()) {
                    log.warn(String.format("File: %s   Line: %d    has something to do."
                            , comment.getRelateFile().getName()
                            , comment.getStartLineNo() + lineNo - 1));
                }
                ++lineNo;
            }
        }

    }

    private TodoChecker() {

    }
}
