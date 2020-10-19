package com.hsm.spock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: StringUtils
 * @description: TODO
 * @date 2020/10/19 18:56
 */
@Slf4j
public class StringUtils {

    /**
     * @return java.util.List<java.lang.String>
     * @description: 根据长度截取字符串
     * @author senming.huang@lachesis-mh.com
     */
    public static List<String> splitByLength(String value, int length) {
        List<String> strList = new ArrayList<>();
        if (isEmpty(value) || length <= 0) {
            log.error("字符串入参[value:{},length:{}]异常", value, length);
            return strList;
        }

        //原字符串长度
        int originalLength = value.length();
        //根据字符串长度计算最终截取的字符列表大小
        int size = (originalLength - 1) / length + 1;
        //将最终截取的字符串放入到字符串列表中
        for (int i = 0; i < size; i++) {
            int startIndex = i * length;
            int endIndex = (i + 1) * length;
            //截止字符串索引
            endIndex = endIndex > originalLength ? originalLength : endIndex;
            strList.add(value.substring(startIndex, endIndex));
        }
        return strList;
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }
}
