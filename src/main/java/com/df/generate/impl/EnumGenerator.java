/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.generate.AbstractGenerator;

/**
 * 枚举数据生成器
 * 
 * @author yejf
 * @date 2014-3-5 下午11:16:00
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class EnumGenerator extends AbstractGenerator {

//	private String[] enumValues; // 枚举
	private Object[] enumValues; //枚举值数组
	// private Class[] values;

	public EnumGenerator() {
	}

	private void init() {
		// TODO Auto-generated method stub
		if (enumValues == null) {  //以免重复执行
			if (getType() == null || getType().trim().length() == 0) {
				throw new RuntimeException("没有指定目标枚举的类型");
			}
			try {
				Class<?> c = Class.forName(getType());
				//获取所有的枚举常量值
				this.enumValues = c.getEnumConstants();
				// 获取所有的 field
				// Field[] fields = c.getDeclaredFields();
				/*Field[] fields = c.getFields();
				this.enumValues = new String[fields.length];
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					// 获取每个属性的值
					String name = field.getName();
					enumValues[i] = name;
				}*/
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				throw new RuntimeException(getType() + " 枚举类型不存在", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		// 选调用init方法
		init();
		int index = (int) (Math.random() * this.enumValues.length);
		return this.enumValues[index];
	}

}
