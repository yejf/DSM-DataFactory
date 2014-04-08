/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.text.NumberFormat;

import com.df.generate.AbstractGenerator;

/**
 * 浮点数生成器
 * @author yejf
 * @date 2014-3-5 下午2:31:58
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class DoubleGenerator extends AbstractGenerator {

	public DoubleGenerator(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		//把父类中的字符串转换成浮点数
		double min = getMin() == null ? -999999.99 : Double.parseDouble(getMin());
		double max = getMax() == null ? 999999.99  : Double.parseDouble(getMax());
//		System.out.println("min = "+min+",max = "+max);
		double result = Math.random()*(max - min);
		result += min;
		//做小数位的确定
		result = convert(result);
		//判断
		if(isUnique()){
			return checkUnique(result);
		}
		return result;
	}


	/*****************
	 * 确定精度
	 * @param result
	 * @return
	 */
	private double convert(double result) {
		int precision = getPrecision() == null ?  1 : Integer.parseInt(getPrecision());
//		System.out.println("result = "+result+", precision = "+precision);
		//格式化
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(precision);
		nf.setMinimumFractionDigits(precision);
		//消取千分位
		nf.setGroupingUsed(false);
		String tmp = nf.format(result);
//		System.out.println("tmp = "+tmp);
		return Double.parseDouble(tmp);
	}

}
