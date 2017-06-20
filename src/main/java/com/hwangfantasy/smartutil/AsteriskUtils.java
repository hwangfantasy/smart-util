package com.hwangfantasy.smartutil;

import com.hwangfantasy.smartutil.verify.CommonVerify;
import org.apache.commons.lang.StringUtils;

/**
 * 各种加星号规则
 * 
 */
public class AsteriskUtils {
	public static final String ASTERISK = "*";
	
	public static boolean isAsterisk(String src) {
	    if (StringUtils.isNotBlank(src)) {
	        return src.contains("*");
	    }
	    return false;
	}
	/**
	 * 手机号加密
	 * @param mobile
	 * @return
	 */
	public static String getMobileWithAsterisk(String mobile) {
		if (CommonVerify.Tel(mobile)) {
			return mobile.substring(0, 3) + ASTERISK + ASTERISK + ASTERISK + ASTERISK + mobile.substring(7, 11);
		}
		return null;
	}
	
	/** 身份证加星号：后6位变星号 */
	public static String getCardNoWithAsterisk(String cardNo) {
		if(StringUtils.isBlank(cardNo) || cardNo.length() < 15){
			return null;
		}
		int length = cardNo.length();
		StringBuffer buf = new StringBuffer();
		buf.append(cardNo.substring(0, length - 6));
		for (int i = 0; i < 6; i++) {
			buf.append(ASTERISK);
		}
		return buf.toString();
	}


	public static String getVinWithAsterisk(String vin) {
		if (vin == null || vin.isEmpty()) {
			return vin;
		}
		int length = vin.length();
		StringBuffer buf = new StringBuffer();
		buf.append(vin.substring(0, 4));
		for (int i = 0; i < length - 8; i++) {
			buf.append(ASTERISK);
		}
		buf.append(vin.substring(length - 4, length));
		return buf.toString();
	}

	/**
	 * 前1位，后3位显示，中间以*号代替
	 * @param engineNo
	 * @return
	 */
	public static String getEngineNoWithAsterisk(String engineNo) {
		if (engineNo == null || engineNo.isEmpty()) {
			return engineNo;
		}
		int length = engineNo.length();
		if (length == 1) {
			return ASTERISK;
		}
		if (length == 2) {
			return engineNo.substring(0, 1) + ASTERISK;
		}
		
		if (length == 3) {
			return engineNo.substring(0, 1) + ASTERISK + engineNo.substring(2, 3);
		}
		
		if (length == 4) {
			return engineNo.substring(0, 1) + ASTERISK + engineNo.substring(2, 4);
		}
		
		StringBuffer buf = new StringBuffer();
		buf.append(engineNo.substring(0, 1));
		for (int i = 0; i < (length - 4); i++) {
			buf.append(ASTERISK);
		}
		buf.append(engineNo.substring(length - 3, length));
		return buf.toString();
	}
	
	/**
	 * 姓名加密，隐藏姓
	 * @param name
	 * @return
	 */
	public static String getNameWithAsterisk(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		if (name.length() >= 2) {
			return AsteriskUtils.ASTERISK + name.substring(1);
		}
		return name;
	}
}
