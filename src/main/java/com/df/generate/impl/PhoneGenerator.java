/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.generate.AbstractGenerator;
import com.df.util.NumberUtil;

/**
 * 手机号码生成器
 * @author yejf
 * @date 2014-3-6 下午7:53:39
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class PhoneGenerator extends AbstractGenerator {

	private static String[] prefix ;
	
	static {
		prefix = new String[]{
				"136","137","138","139",
				"181","182","183","184","185","186","189"
		};
	}
	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		//产生前缀
		String first = prefix[(int)(Math.random()*prefix.length)];
		builder.append(first);
		//产生第二部份
		String sec = NumberUtil.genSequence(8);
		builder.append(sec);
		
		//判断
		if(isUnique()){
			return checkUnique(builder.toString());
		}
		return builder.toString();
	}

}
