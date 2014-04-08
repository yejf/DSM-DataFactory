/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.util.DateUtil;
import com.df.util.NumberUtil;

/**
 * 身份证号生成器
 * 它继承于 DateGenerator,所以，可以指定日期的范围，以便生成在此日期范围内的身份证号码
 * @author yejf
 * @date 2014-3-5 下午11:17:14
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class IdCardGenerator extends DateGenerator {
	
	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		//身份证生成的规则是：
		//前6位[随机] + 年月日+ 后三位[随机]+最后一位[0~9]或X
		//创建 StringBuilder
		StringBuilder builder = new StringBuilder();
		//生成前6位
		builder.append(NumberUtil.genIdCard6Bits());
		//获取父类的中日期值,以生成中间8位
		builder.append(DateUtil.toString(getDate(), "yyyyMMdd"));
		//获取最后四位
		builder.append(NumberUtil.genIdCard4Bits());
		//判断是否重复
		if(isUnique()){
			return checkUnique(builder.toString());
		}
		return builder.toString();
	}

}
