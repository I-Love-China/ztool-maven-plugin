package com.aliyun.code.typist.components.content;

import java.io.File;
import java.util.List;

// 表示文件内容
public interface Content {
    /**
     * 获取文件内容关联的文件
     *
     * @return
     */
    public File getRelateFile();

    /**
     * 保存的文本行的起始行号
     *
     * @return
     */
    public int getStartLineNo();

    /**
     * 保存的文本行
     *
     * @return
     */
    public List<String> getLines();

    /**
     * 给定偏移量，获取对应的行号
     *
     * @param offset 相对于当前保存的内容的偏移量
     * @return
     */
    public int getLineNoByOffset(int offset); //

    /**
     * @param offset 相对于整个文件内容的偏移量
     * @return
     */
    public int getLineNoByGlobalOffset(int offset);
}
