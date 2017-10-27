package com.wangenyong.mvp.utils;

import android.text.TextUtils;

/**
 *
 * @author wangenyong
 * @date 2017/10/27
 */

public class StringUtil {

    /**
     * 判断是否是手机号
     * @param num
     * @return
     * 中国移动手机号码开头数字 134、135、136、137、138、139、150、151、152、157、158、159、182、183、184、187、188、178(4G)、147(上网卡)
     * 中国联通手机号码开头数字 130、131、132、155、156、185、186、176(4G)、145(上网卡)
     * 中国电信手机号码开头数字 133、153、180、181、189 、177(4G)
     * 另外还有177开头
     */
    public static boolean checkMobileNum(String num) {
        int mobileNumLength = 11;
        if (TextUtils.isEmpty(num) || num.length() != mobileNumLength) {
            return false;
        }
        // 中国移动
        String[] chinaMobile = new String[] { "134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "182", "183", "184", "187", "188", "178", "147" };
        // 中国联通
        String[] chinaUnicom = new String[] { "130", "131", "132", "155", "156", "185", "186", "176", "145" };
        // 中国电信
        String[] chinaTelecom = new String[] { "133", "153", "180", "181", "189", "177" };
        // 其他
        String[] chinaOther = new String[] { "170", "171" };

        for (String n : chinaMobile) {
            if (num.startsWith(n)) {
                return true;
            }
        }
        for (String n : chinaUnicom) {
            if (num.startsWith(n)) {
                return true;
            }
        }
        for (String n : chinaTelecom) {
            if (num.startsWith(n)) {
                return true;
            }
        }
        for (String n : chinaOther) {
            if (num.startsWith(n)) {
                return true;
            }
        }
        return false;
    }
}
