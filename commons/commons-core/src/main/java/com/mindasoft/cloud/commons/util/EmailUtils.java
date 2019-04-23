package com.mindasoft.cloud.commons.util;

import java.util.regex.Pattern;

/**
 * @author: min
 * @date: 2019/4/23 14:26
 * @version: 1.0.0
 */
public class EmailUtils {

    public static boolean isEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }
}
