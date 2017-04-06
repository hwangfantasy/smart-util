package com.hwangfantasy.smartutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者 yunfeiyang
 * @创建时间: 2017/4/6 <br/>
 * @方法描述: 账户验证. <br/>
 */

public class AccountValidate {
    /**
     * @作者: yunfeiyang
     * @创建日期: 2017/4/6 9:36
     * @方法描述:  校验邮箱合法性
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * @作者: yunfeiyang
     * @创建日期: 2017/4/6 9:43
     * @方法描述:  手机号校验
     */
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
}
