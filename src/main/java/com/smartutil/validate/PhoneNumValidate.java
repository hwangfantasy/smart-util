package com.smartutil.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 验证手机号码合法性；暂未改成正则表达式验证形式...
 * Created by yunfeiyang on 2016/12/2.
 */
public class PhoneNumValidate {
    /**
     * 验证手机号码
     * @param mobileNumber
     * @return
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

