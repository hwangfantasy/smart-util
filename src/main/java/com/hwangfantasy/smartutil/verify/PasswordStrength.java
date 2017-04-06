package com.hwangfantasy.smartutil.verify;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者 hwangfantasy
 * @创建时间: 2017/4/6 <br/>
 * @方法描述: 密码强度校验. <br/>
 */

public class PasswordStrength {
    /**
     * @作者: hwangfantasy
     * @创建日期: 2017/4/6 9:45
     * @方法描述:  密码强度校验
     */
    public static Safelevel checkPasswordStrength(String c) {
        Safelevel d = Safelevel.WEAK;
        if (isEmptyPassword(c)) {
            return d;
        }
        if (isTooShort(c)) {
            d = Safelevel.WEAK;
        } else {
            if (hasNum(c) && hasLetter(c) && hasSymbol(c)) {
                d = Safelevel.SECURE;
            } else {
                if (hasNum(c) && hasLetter(c)) {
                    d = Safelevel.STRONG;
                } else {
                    if (hasNum(c) && hasSymbol(c)) {
                        d = Safelevel.STRONG;
                    } else {
                        if (hasSymbol(c) && hasLetter(c)) {
                            d = Safelevel.STRONG;
                        } else {
                            if (isAllNum(c) || isAllLetter(c) || isAllSymbol(c)) {
                                d = Safelevel.WEAK;
                            }
                        }
                    }
                }
            }
        }
        return d;
    }

    public enum Safelevel {

        WEAK, /* 弱 */

        STRONG, /* 强 */

        SECURE, /* 安全 */

    }

    public static boolean hasNum(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean hasSymbol(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*[a-zA-Z0-9\\s<>;'\\\\]+.*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean isAllSymbol(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile("^[a-zA-Z0-9\\s<>;'\\\\]+$");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean hasSpace(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\s+.*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean hasIllegalSymbol(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*[\\s<>;'\\\\].*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean hasLetter(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*[a-zA-Z]+.*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean isAllLetter(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    private static boolean isEmptyPassword(String b) {
        return (b == null || b.length() == 0);
    }

    private static boolean isTooShort(String b) {
        return b.length() < 6;
    }

    public static boolean isAllNum(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile("^\\d+$");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean hasRepeat6Chars(String content) {

        boolean flag = false;
        Pattern p = Pattern.compile(".*([0-9a-zA-Z])\\1{5}.*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;

    }

    public static boolean hasIncrease6Chars(String g) {

        if (g == null || g.length() < 6) {
            return false;
        }
        char h = g.charAt(0);
        char i = 1;
        char j = 1;
        for (j = 1; j < g.length(); j++) {
            char f = g.charAt(j);
            if (f == h + 1) {
                i++;
                if (i >= 6) {
                    return true;
                }
            } else {
                i = 1;
            }
            h = f;
        }
        return false;

    }

    public static boolean hasDecrease6Chars(String g) {

        if (g == null || g.length() < 6) {
            return false;
        }
        char h = g.charAt(0);
        char i = 1;
        char j = 1;
        for (j = 1; j < g.length(); j++) {
            char f = g.charAt(j);
            if (f == h - 1) {
                i++;
                if (i >= 6) {
                    return true;
                }
            } else {
                i = 1;
            }
            h = f;
        }
        return false;

    }

    public static boolean hasAllIncreaseChars(String g) {

        if (g == null) {
            return false;
        }
        int i = g.length();
        char h = g.charAt(0);
        char j = 1;
        char k = 1;
        for (k = 1; k < g.length(); k++) {
            char l = g.charAt(k);
            if (l == h + 1) {
                j++;
                if (j >= i) {
                    return true;
                }
            } else {
                j = 1;
            }
            h = l;
        }
        return false;

    }

    public static boolean hasAllDecreaseChars(String g) {
        if (g == null) {
            return false;
        }
        int i = g.length();
        char h = g.charAt(0);
        char j = 1;
        char k = 1;
        for (k = 1; k < i; k++) {
            char l = g.charAt(k);
            if (l == h - 1) {
                j++;
                if (j >= i) {
                    return true;
                }
            } else {
                j = 1;
            }
            h = l;
        }
        return false;

    }

    public static boolean isAllSameChars(String content) {

        if (content == null || content.length() < 2) {
            return false;
        }
        char h = content.charAt(0);
        char e = 1;
        for (e = 1; e < content.length(); e++) {
            char f = content.charAt(e);
            if (f != h) {
                return false;
            }
        }
        return true;

    }

}

