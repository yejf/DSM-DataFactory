/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 抽象实现类，为具体的数据产生器提供通用的操作
 * 
 * @author yejf
 * @date 2014-3-5 下午3:55:31
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public abstract class AbstractGenerator implements IDataGenerator {

	protected static Logger log = Logger.getLogger(AbstractGenerator.class);
	
	//所有有可能要配置的属性
	private boolean unique; // 是否唯一
	private Set<Object> cache; // 用来判断是否产生了重复的容器
	
	private String min;				//最小范围
	private String max;			//最大范围
	private String value;			//自定义值
	private String type;			//枚举的类型
	private String length;		//姓名的长度
	private String precision;	//浮点数的精度
	
	private String suffix;		//针对英文名的后缀， 是否需要
	private String prefix;		//针对英文名的前缀， 是否需要

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public AbstractGenerator() {
		super();
	}

	public AbstractGenerator(boolean unique) {
		super();
		this.unique = unique;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	/*********************
	 * 判断容器中是否已经存了指定 的对象
	 * 
	 * @param value
	 * @return
	 */
	protected Object checkUnique(Object value) {
		// TODO Auto-generated method stub
		if (cache == null)
			cache = new HashSet<Object>();
		
		if (cache.contains(value)) {
			log.debug("重复了...");
//			System.out.println("这个重复了....");
			return generate();
		} else {
			cache.add(value);
			return value;
		}
	}

}
