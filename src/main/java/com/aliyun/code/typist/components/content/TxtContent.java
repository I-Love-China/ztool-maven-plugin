package com.aliyun.code.typist.components.content;

import com.aliyun.code.typist.components.util.Util;
import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class TxtContent implements Content{
    private final int[] lineOffsets; // 保存每一行起始字符的偏移量， 索引对应行号，值对应字符偏移量
    private final List<String> lines; // 每一行的内容
    private final File srcFile;


    public TxtContent(File file) throws IOException {
        this.srcFile = file;
        this.lines = FileUtils.readLines(file, Charsets.UTF_8);
        this.lineOffsets = Util.lineOffsets(0, lines);
    }

    @Override
    public int getLineNoByOffset(int offset) {
        return Util.findClosestSmall(lineOffsets, offset) + 1;
    }

    @Override
    public int getLineNoByGlobalOffset(int offset) {
        return Util.findClosestSmall(lineOffsets, offset) + 1;
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public File getRelateFile() {
        return this.srcFile;
    }

    @Override
    public int getStartLineNo() {
        return 1;
    }

    @Override
    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }


}