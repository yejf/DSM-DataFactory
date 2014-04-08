/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.Date;

import com.df.generate.AbstractGenerator;
import com.df.util.DateUtil;

/**
 * 日期数据生成器
 * 
 * @author yejf
 * @date 2014-3-5 下午11:17:41
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class DateGenerator extends AbstractGenerator {

	public DateGenerator() {
		super();
		init();
	}

	private void init() {
		//判断用户是否指定了 min 与 max
		if(getMin() == null || getMin().trim().length() == 0){
			//给默认值 
			setMin("1921-01-01");
		}
		if(getMax() == null || getMax().trim().length() == 0){
			//给默认值
			setMax(DateUtil.toString(new Date()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.df.generate.IDataGenerator#generate() 90-10, 91 - 1
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		Date d = getDate();
		// 排重
		if (isUnique()) {
			return checkUnique(d);
		}
		return d;
	}

	/******************
	 * 利用时间范围 来随机生成一个时间
	 * @return
	 */
	protected Date getDate() {
		Date min = DateUtil.buildDate(getMin());
		Date max = DateUtil.buildDate(getMax());
		// 利用两个日期相减后来产生一个随机数，再加上原来的最小时间，即可以随机生成一个有效范围 时间
		long step = min.getTime()
				+ (long) (Math.random() * (max.getTime() - min.getTime()));
		Date d = new Date(step);
		return d;
	}
}
