package com.hwangfantasy.smartutil.object;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/*******
 * @author Bang
 * 
 *         deserialization() json反序列化 serialization() json序列化
 * 
 **/
public class FastJson {

    public static <T> T deserialization(String json, Class<T> c) {
        T t = null;
        try {
            t = JSON.parseObject(json, c);
        } catch (Exception e) {
            System.out.println("请求参数转换成对象失败:" + e);
        }
        return t;
    }

    public static String serialization(Object o) {
        String json = "";
        try {
            json = JSON.toJSONString(o);
        } catch (Exception e) {
            System.out.println("返回对象转换成json字符串异常:" + e);
        }
        return json;
    }

    public static <T> List<T> getList(String json, Class<T> c) throws Exception {
        JSONArray array = JSONArray.fromObject(json);
        List<T> list = new ArrayList<T>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < array.size(); i++) {
                T t = objectMapper.readValue(array.getString(i), c);
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println("json数组转成对象数组失败:" + e);
        }
        return list;
    }
}
