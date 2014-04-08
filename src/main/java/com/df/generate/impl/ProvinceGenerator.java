/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.data.Area;

/**
 * 省份产生器
 * @author yejf
 * @date 2014-3-5 下午5:21:11
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class ProvinceGenerator extends AddressGenerator {
	
	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		int index = (int)(Math.random() * areaList.size());
		Area province = areaList.get(index);
		String result = province.getName();
		//判断是否重复
		if(isUnique()){
			return checkUnique(result);
		}
		return result;
	}

}

