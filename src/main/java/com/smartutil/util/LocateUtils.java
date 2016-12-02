package com.smartutil.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图定位
 */
public class LocateUtils {

	private static Logger LOGGER = Logger.getLogger(LocateUtils.class);
	
	// 百度接口
	private static String LOCATE_BY_IP_URL = "http://api.map.baidu.com/location/ip?ak=OkwT9yQB8ulYnrq3dqNcE5LN";
	private static String LOCATE_BY_LOCATION_URL = "http://api.map.baidu.com/geocoder/v2/?ak=OkwT9yQB8ulYnrq3dqNcE5LN";
	
	/**
	 * 根据IP定位
	 * @param ip
	 * @return
	 */
	public static String locateBy(String ip) {
		if (!valideIp(ip)) {
			return null;
		}

		Map<String, String> paras = new HashMap<String, String>(1);
		paras.put("ip", ip);
		String response = null;

		try {
			long beginTime = System.currentTimeMillis();
			response = HttpUtils.httpPost(LOCATE_BY_IP_URL, paras, "utf-8","GBK");
			//System.out.println("百度ip用时： "+(System.currentTimeMillis() - beginTime));
			LOGGER.info("locate time for ip=" + ip + " ; spendTime="
					+ (System.currentTimeMillis() - beginTime) + "; cityName="
					+ response);
		} catch (Exception e) {
			LOGGER.error("locate error with ip=" + ip + " for "
					+ e.getMessage());
			e.printStackTrace();
		}

		if (response == null || response.isEmpty()) {
			return null;
		}

		try {
			String city = "";
			JSONObject jsonObject = JSONObject.parseObject(response);
			
			if (jsonObject.containsKey("content")) {
				JSONObject js = jsonObject.getJSONObject("content").getJSONObject("address_detail");
				city = js.getString("city");
			}			

			if (!city.equalsIgnoreCase("")) {
				return city;
			} else {
				LOGGER.info("locate time for ip=" + ip + " ; jsonObj="+ jsonObject + "; cityName=" + response);
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static boolean valideIp(String ip) {
	    //ipv6的localhost也要考虑
		if (ip == null || ip.isEmpty() || ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
			return false;
		}
		return true;
	}

	/**
	 * 根据经纬度定位
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public static String locateBy(double longitude, double latitude) {
		Map<String, String> paras = new HashMap<String, String>(4);
		paras.put("location", latitude + "," + longitude);
		paras.put("callback", "renderReverse");
		paras.put("output", "json");
		paras.put("pois", "0");
		String response = null;

		try {
			long beginTime = System.currentTimeMillis();
			response = HttpUtils.httpPost(LOCATE_BY_LOCATION_URL, paras,"utf-8", "utf-8");
			//System.out.println("百度经纬度用时: "+(System.currentTimeMillis() - beginTime));
			LOGGER.info("locate time for longitude=" + longitude
					+ " and latitude=" + latitude + " : "
					+ (System.currentTimeMillis() - beginTime));
		} catch (Exception e) {
			LOGGER.error("locate error with longitude=" + longitude
					+ " and latitude=" + latitude + ".", e);
		}

		if (response == null || response.isEmpty()) {
			return null;
		}

		try {
			JSONObject jsonObject = JSONObject.parseObject(response);
			String status = jsonObject.getString("status");
			if (!status.equalsIgnoreCase("0")) {
				LOGGER.error("locate error with longitude=" + longitude
						+ " and latitude=" + latitude);
				return null;
			}
			if (jsonObject.containsKey("result")) {
				JSONObject addressComponent = jsonObject.getJSONObject("result").getJSONObject("addressComponent");
				String city = addressComponent.getString("city");
				if (city != null && !city.isEmpty()) {
					return city;
				} else {
					LOGGER.error("locate error with longitude=" + longitude
							+ " and latitude=" + latitude + " for "
							+ jsonObject.toString());
					return null;
				}
			}
		} catch (Exception e) {
			LOGGER.error("locate error with response=" + response + ".", e);
		}

		return null;
	}
	/**
	 * 根据城市的code, 获取省的area code
	 * @param cityAreaCode
	 * @return
	 */
	public static String getProvinceByCityCode(String cityAreaCode) {
	    if (StringUtils.isNotBlank(cityAreaCode)) {
	        return cityAreaCode.substring(0, 2) + "0000";
	    }
	    return cityAreaCode;
	}
}
