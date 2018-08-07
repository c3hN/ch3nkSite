package com.ch3nk.ch3nkSite.modules.utils;

import org.apache.commons.lang3.StringUtils;

public class SQLUtil {
    /** 模糊查询特殊字符转义处理 */
    public static String escapeLike(String s) {
        if (StringUtils.isNotEmpty(s)) {
            return '%' + s.replaceAll("/", "///").replaceAll("%", "/%").replaceAll("_", "/_") + '%';
        }
        return null;
    }

    /** 后模糊查询特殊字符转义处理 */
    public static String escapePrefixLike(String s) {
        if (StringUtils.isNotEmpty(s)) {
            return s.replaceAll("/", "///").replaceAll("%", "/%").replaceAll("_", "/_") + '%';
        }
        return null;
    }

    /** 前模糊查询特殊字符转义处理 */
    public static String escapeSuffixLike(String s) {
        if (StringUtils.isNotEmpty(s)) {
            return '%' + s.replaceAll("/", "///").replaceAll("%", "/%").replaceAll("_", "/_");
        }
        return null;
    }
}