package com.mindasoft.cloud.commons.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Map工具类
 *
 * @author Mark huangmin@sina.com
 * @since 3.0.0
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static TreeMap bean2TreeMap(Object javaBean) {
        TreeMap map = new TreeMap();

        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean, new Object[0]);
                        map.put(propertyName, propertyValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String map2String(Map<String,Object> paramMap, String sign){
        StringBuilder rawSignature = new StringBuilder();
        Iterator<Entry<String,Object>> it = paramMap.entrySet().iterator();
        while(it.hasNext()){
            Entry entry = it.next();
            String value = null;
            value = entry.getValue().toString();
            rawSignature.append(entry.getKey()).append("=").append(value).append("&");
        }
        rawSignature.append("sign=").append(sign);
        return rawSignature.toString();
    }
}
