package com.chenxin.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author 未知 2018-09-29
 */
public class JsonUtils {

	private static Logger log = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * 将对象转换成json字符串。
	 * @param data
	 * @return
	 */
	public static String toJSONString(Object data) {
		ObjectMapper objectMapper = new ObjectMapper();
		String result = null;
		try {
			result = data != null ? objectMapper.writeValueAsString(data) : null;
		} catch (JsonProcessingException e) {
			log.error("对象转换json出现异常", e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将对象转换成json数组
	 * @param data
	 * @return
	 */
	public static byte[] toJSONByte(Object data) {
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] result = null;
		try {
			result = data != null ? objectMapper.writeValueAsBytes(data) : null;
		} catch (JsonProcessingException e) {
			log.error("对象转换json出现异常", e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将json字符串转化为Map对象
	 * @param jsonData
	 * @return 
	 */
	public static Map<String, Object> parseJSONObject(String jsonData) {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
		Map<String, Object> map = null;
		try {
			map = StringUtils.isNotBlank(jsonData) ? objectMapper.readValue(jsonData, javaType) : null;
		} catch (Exception e) {
			log.error("json转换Map集合出现异常", e);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将json结果集转化为对象
	 * @param jsonData json数据
	 * @param beanType 对象中的object类型
	 * @return
	 */
	public static <T> T parseJSONObject(String jsonData, Class<T> beanType) {
		ObjectMapper objectMapper = new ObjectMapper();
		T t = null;
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			t = StringUtils.isNotBlank(jsonData) ? objectMapper.readValue(jsonData, beanType) : null;
		} catch (Exception e) {
			log.error("json转换对象出现异常", e);
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * @param <T>
	 * @param jsonData
	 * @param beanType1
	 * @param beanType2
	 * @return
	 */
	public static <T> T parseJSONObject(String jsonData, Class<T> beanType1, Class<?> beanType2) {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType javaType2 = null;
		JavaType javaType = null;
		if (Map.class.isAssignableFrom(beanType2)) {
			javaType2 = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
			javaType = objectMapper.getTypeFactory().constructParametricType(beanType1, javaType2);
		} else {
			javaType = objectMapper.getTypeFactory().constructParametricType(beanType1, beanType2);
		}
		T t = null;
		try {
			t = StringUtils.isNotBlank(jsonData) ? objectMapper.readValue(jsonData, javaType) : null;
		} catch (Exception e) {
			log.error("json转换Bean<bean>自定义对象泛型出现异常", e);
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> parseJSONArray(String jsonData, Class<T> beanType) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, beanType);
		List<T> list = null;
		try {
			list = StringUtils.isNotBlank(jsonData) ? objectMapper.readValue(jsonData, collectionType) : null;
		} catch (Exception e) {
			log.error("json转换List集合出现异常", e);
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 将json数据转换成pojo对象list
	 * @param <K>
	 * @param <V>
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <K, V> List<Map<K, V>> parseJSONArrayMap(String jsonData, Class<K> keyType, Class<V> valueType) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
	    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, javaType);
	    List<Map<K, V>> list = null;
	    try {
	        list = StringUtils.isNotBlank(jsonData) ? objectMapper.readValue(jsonData, collectionType) : null;
	    } catch (Exception e) {
	        log.error("json转换List集合出现异常", e);
	        e.printStackTrace();
	    }
	    return list;
	}
	
	/**
	 * 将json数据转换成pojo对象Map
	 * @param jsonData
	 * @param beanType1
	 * @param beanType2
	 * @return
	 */
	public static <K, V> Map<K, V> parseJSONMap(String jsonData, Class<K> beanType1, Class<V> beanType2){
	    ObjectMapper objectMapper = new ObjectMapper();
	    if(beanType1 == null || beanType2 == null) {
	        return null;
	    }
	    JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, beanType1, beanType2);
        Map<K, V> map = null;
        try {
            map = StringUtils.isNotBlank(jsonData) ? objectMapper.readValue(jsonData, javaType) : null;
        } catch (Exception e) {
            log.error("json转换Map集合出现异常", e);
            e.printStackTrace();
        }
        return map;
	}
}