package com.yeahwell.epu.common.util;

import java.util.regex.Pattern;

public class RegexValidateUtil {
	
	private RegexValidateUtil(){}
	
    /**
     * 用于匹配从0到9的数字;
     */
    public final static String NUM   = "[0-9]*";

    /**
     * 用于匹配字母，数字或下划线字符;
     */
    public final static String WORLD = "^\\w+$";

    /**
     * 用于金额，只能输入两位小数
     */
    public final static String AMT   = "^[0-9]+(.[0-9]{2})?$";

    public static boolean validate(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string).matches();
    }

}