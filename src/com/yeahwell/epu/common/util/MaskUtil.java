package com.yeahwell.epu.common.util;

/**
 * 
 * 功能描述： 负责对指定字符串进行掩码
 * @author 作者 yeahwell19920525@gmail.com
 * @created 2014-2-15 下午9:57:38
 * @version 1.0.0
 * @date 2014-2-15 下午9:57:38
 */
public class MaskUtil {
	
	private MaskUtil(){
		
	}

    /**
     * 功能描述：对姓名进行掩码
     * @param name 被掩码的姓名
     * @return String 掩码后的姓名
     */
    public static String maskName(String name) {
        String nameAfterMask = "";
        if (null == name) {
            return null;
        } else {
            if (name.length() >= 1) {
                nameAfterMask = "**" + name.substring(name.length() - 1, name.length());
            } else {
                nameAfterMask = name;
            }
        }
        return nameAfterMask;
    }

    /**
     * 功能描述：对手机号进行掩码
     * @param cellphone 被掩码的手机号
     * @return String 掩码后的手机号
     */
    public static String maskCellphone(String cellphone) {
        String cellphoneAfterMask = "";
        if (null == cellphone) {
            return null;
        } else {
            if (cellphone.length() >= 11) {
                cellphoneAfterMask = cellphone.substring(0, 3) + "****"
                            + cellphone.substring(cellphone.length() - 4, cellphone.length());
            } else {
                cellphoneAfterMask = cellphone;
            }
        }
        return cellphoneAfterMask;
    }

    public static void main(String[] args) {
        // 测试姓名掩码
        System.out.println(MaskUtil.maskName(null));
        System.out.println(MaskUtil.maskName("欧阳夏丹"));
        System.out.println(MaskUtil.maskName("颜佳伟"));
        System.out.println(MaskUtil.maskName("王菲"));
        System.out.println(MaskUtil.maskName("夏"));

        // 测试手机号掩码
        System.out.println(MaskUtil.maskCellphone(null));
        System.out.println(MaskUtil.maskCellphone("18616968346"));
        System.out.println(MaskUtil.maskCellphone("1234567890123456789"));
        System.out.println(MaskUtil.maskCellphone("12345"));
        System.out.println(MaskUtil.maskCellphone("12"));
    }

}
