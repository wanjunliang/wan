/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2015 All Rights Reserved.
 */
package com.neusoft.smis.common.unit;


import com.google.common.base.Strings;
import com.neusoft.smis.common.enums.SysErrorEnum;
import com.neusoft.smis.common.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;

/**
 * json转换工具
 * @author tangwei
 * @created on 2016-09-15 16:59
 */
@Slf4j
public final class JsonUtils {
	/**
	 * 工具类对象
	 **/
	public final static JsonUtils instance = JsonUtils.buildNormalBinder();
	/**
	 * 配置对象
	 **/
	private ObjectMapper mapper;

	private JsonUtils(JsonSerialize.Inclusion inclusion) {
		mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		mapper.getSerializationConfig().withSerializationInclusion(inclusion);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 禁止使用int代表Enum的order()來反序列化Enum,非常危險
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
		//
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	}

	/**
	 * 创建输出全部属性到Json字符串的Binder。
	 */
	private static JsonUtils buildNormalBinder() {
		return new JsonUtils(JsonSerialize.Inclusion.ALWAYS);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串，返回Null. 如果JSON字符串为"[]"，返回空集合.
	 * 如需读取集合如List/Map，且不是List<String>这种简单类型时，使用如下语句：
	 * List<MyBean> beanList = fromJson(listString, new TypeReference<List<MyBean>>() {});
	 * 建议使用 fromJson(String jsonString, JavaType javaType)。
	 *
	 * @param jsonString  json字符串
	 * @param entityClass 类
	 * @param <T>         泛型类
	 * @return 返回转换后的对象
	 * @throws Exception 异常
	 */
	public <T> T fromJson(String jsonString, Class<T> entityClass) throws SysException {
		try {
			if (!Strings.isNullOrEmpty(jsonString))
				return mapper.readValue(jsonString, entityClass);
			return null;
		} catch (IOException e) {
			log.error("JSON字符串转换错误:{}，参数：{}", e.getMessage(), jsonString);
			throw new SysException(SysErrorEnum.JSON_ERROR);
		}
	}

	/**
	 * 读取集合如List/Map，且不是List<String>時，
	 * 先用constructParametricType(List.class,MyBean.class)构造出JavaType，再调用本函数.
	 *
	 * @param jsonString json字符串
	 * @param javaType   类型
	 * @param <T>        返回对象
	 * @return json字符串转为T的对象
	 * @throws Exception 异常
	 */
	public <T> T fromJson(String jsonString, JavaType javaType) throws Exception {
		try {
			if (!Strings.isNullOrEmpty(jsonString))
				return (T) mapper.readValue(jsonString, javaType);
			return null;
		} catch (IOException e) {
			throw new Exception("JSON字符串转换错误", e);
		}
	}


	/**
	 * 构造泛型的Type如List<MyBean>, Map<String,MyBean>
	 *
	 * @param parametrized     类变量
	 * @param parameterClasses 类参数
	 * @return JavaType
	 */
	public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
		return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}

	/**
	 * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖该部分的属性。
	 *
	 * @param object     对象
	 * @param jsonString 对象的json字符串
	 * @param <T>        对象
	 * @return 更新后的对象
	 * @throws Exception 异常
	 */
	@SuppressWarnings("unchecked")
	public <T> T update(T object, String jsonString) throws Exception {
		try {
			if (!Strings.isNullOrEmpty(jsonString)) {
				return (T) mapper.readerForUpdating(object).readValue(jsonString);
			}
			return object;
		} catch (IOException e) {
			throw new Exception("JSON字符串更新对象错误", e);
		}
	}

	/**
	 * 如果对象为Null，返回"null"。如果集合为空集合，返回"[]"。
	 *
	 * @param object 需要转为json字符串的对象
	 * @return json字符串
	 * @throws Exception 转换异常
	 */
	public String toJson(Object object) throws Exception {
		try {
			if (null == object) return null;
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new Exception("JSON字符串更新对象错误", e);
		}
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API。
	 *
	 * @return
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}

}
