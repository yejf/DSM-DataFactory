/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.generate.AbstractGenerator;

/**
 * 整数产生器
 * 可以指定整数的范围、是否重复[默认可以重复]
 * @author yejf
 * @date 2014-3-5 下午12:52:01
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class IntegerGenerator extends AbstractGenerator {

	public IntegerGenerator() {
		super();
	}

	/* 
	 * 产生整数，依据于此类的属性
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub  -19 , 20, (15 ~ 28)
		int min = (getMin() == null ? Integer.MIN_VALUE  / 2 : Integer.parseInt(getMin()));
		int max = (getMax() == null ? Integer.MAX_VALUE  / 2 : Integer.parseInt(getMax()));
		
		//产生一个随机数
		Integer result = (int)(Math.random()*(max - min)) + 1;
		if(result == 1){
			result = 0;	//考虑下限的值
		}
		result += min;
		if(isUnique()) {
			return checkUnique(result);
		}
		//允许重复的话，就直接返回 result
		return result;
	}

}
