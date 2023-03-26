package com.wang.common.util;

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Random;

public final class StringUtil {

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    private StringUtil() {

    }

    public static boolean isEmptyOfStrict(String str) {
        boolean flag = false;
        if (StringUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获得文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getFileType(String fileName) {
        if (StringUtils.isEmpty(fileName) || !fileName.contains(".")) {
            return "unknown";
        }
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length - 1;
        return strArray[suffixIndex];
    }


    /**
     * 生成随机字符串
     *
     * @param size 字符串长度
     */
    public static String generateNonce(int size) {
        char[] nonceChars = new char[size];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

}
