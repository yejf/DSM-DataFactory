/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.generate.AbstractGenerator;

/**
 * 通用字符串数据生成器，依赖于用户自己配置的数据
 * @author yejf
 * @date 2014-3-5 下午11:19:56
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class CommonStringGenerator extends AbstractGenerator {

	protected final String REGEX = ",";
	
	public CommonStringGenerator() {
		super();
		init();
	}

	/*****************
	 * 初始化数据
	 */
	private void init() {
		// TODO Auto-generated method stub
		if(getValue() == null || getValue().trim().length() == 0){
			String value = "游戏,电影,购物,看书,音乐,学习,书法,编程,足球,蓝球,羽毛球,桌球,美食";// 提供默认值
			setValue(value);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		//处理成字符串
		String[] datas = getValue().split(REGEX);
		int index = (int)(Math.random() * datas.length);
		return datas[index];
	}

}
