package com.aliyun.code.typist.components.util;

import com.google.common.primitives.Ints;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Util {
    public static int findClosestSmall(int[] arr, int value) {
        if (value > arr[arr.length - 1]) {
            return arr.length - 1;
        }

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = (hi + lo) / 2;

            if (value < arr[mid]) {
                hi = mid - 1;
            } else if (value > arr[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return (arr[lo] - value) < 0 ? lo : lo - 1;
    }

    public static int[] lineOffsets(int startOffset, List<String> lines) {
        List<Integer> lineOffsets = new ArrayList<>();
        lineOffsets.add(0);

        for (String line : lines) {
            startOffset += line.length() + 1;
            lineOffsets.add(startOffset);
        }

        return Ints.toArray(lineOffsets);
    }

    public static boolean isJavaFile(File file) {
        return null != file && file.exists() && file.isFile() && file.getName().endsWith(".java");
    }
}
