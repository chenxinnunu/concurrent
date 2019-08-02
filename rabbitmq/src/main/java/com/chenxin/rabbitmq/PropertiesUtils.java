package com.chenxin.rabbitmq;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author 未知 2018-09-29
 */
public final class PropertiesUtils extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        ctxPropertiesMap = Maps.newConcurrentMap();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
        System.out.println(ctxPropertiesMap);
        System.out.println("我是loadProperties"+ctxPropertiesMap.get("znxd.sftp.upload.host"));;
    }

    /**
     * Get a value based on key , if key does not exist , null is returned
     * 
     * @param key
     * @return
     */
    public static String getString(String key) {
        return ctxPropertiesMap.get(key);
    }

    /**
     * 根据key获取值
     * 
     * @param key
     * @return
     */
    public static int getInt(String key) {
        String value = ctxPropertiesMap.get(key);
        return Objects.equals(value, StringConstants.EMPTY) ? Integer.parseInt(ctxPropertiesMap.get(key)) : 0;
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        String value = ctxPropertiesMap.get(key);
        return Objects.equals(value, StringConstants.EMPTY) ? Boolean.parseBoolean(ctxPropertiesMap.get(key)) : false;
    }

    /**
     * 根据key获取值
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (Objects.equals(value, StringConstants.EMPTY)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    /**
     * 根据key获取值
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (Objects.equals(value, StringConstants.EMPTY)) {
            return defaultValue;
        }
        return new Boolean(value);
    }

}