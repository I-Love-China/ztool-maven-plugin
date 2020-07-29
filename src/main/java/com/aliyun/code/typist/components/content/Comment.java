package com.aliyun.code.typist.components.content;

import com.aliyun.code.typist.components.util.Util;

import java.io.File;
import java.util.Collections;
import java.util.List;


public class Comment implements Content {
    private final File relateFile;
    private int startLineNo;
    private int startOffset;
    private List<String> commentLines;
    private int[] lineOffsets;

    public Comment(int startLineNo, int startOffset, List<String> lines, File relateFile) {
        this.startLineNo = startLineNo;
        this.startOffset = startOffset;
        this.commentLines = lines;
        this.relateFile = relateFile;

        lineOffsets = Util.lineOffsets(0, lines);
    }

    @Override
    public File getRelateFile() {
        return this.relateFile;
    }

    @Override
    public int getStartLineNo() {
        return startLineNo;
    }

    @Override
    public List<String> getLines() {
        return Collections.unmodifiableList(commentLines);
    }

    @Override
    public int getLineNoByOffset(int offset) {
        return Util.findClosestSmall(lineOffsets, offset) + 1;
    }

    @Override
    public int getLineNoByGlobalOffset(int offset) {
        return getLineNoByOffset(offset - startOffset) + startLineNo;
    }
}
