/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数组数据生成器 
 * @author yejf
 * @date 2014-3-5 下午11:16:26
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class ArrayGenerator extends CommonStringGenerator {

	public ArrayGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		//处理成字符串
		String[] datas = getValue().split(REGEX);
		List<String> list = Arrays.asList(datas);
		Collections.shuffle(list);
		int length = (int)(Math.random() * datas.length);
		//最处一下最大值与最小值
		length = length > 5 ? 5 : length;
		length = length == 0 ? 1 : length;
		
		String[] result = new String[length];
		for(int i = 0;i<result.length;i++){
			result[i] = list.get(i);
		}
		
		return result;
	}

}
