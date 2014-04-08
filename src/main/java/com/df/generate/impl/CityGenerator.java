/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.List;

import com.df.data.Area;

/**
 * @author yejf
 * @date 2014-3-5 下午5:48:12
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class CityGenerator extends AddressGenerator {

	@Override
	public Object generate() {
		// TODO Auto-generated method stub
		// 随机从集合中产生一个省份
		int index = (int) (Math.random() * areaList.size());
		Area province = areaList.get(index);
		// 从它的子元素中随机选取一个城市
		List<Area> cityList = province.getChildren();
		index = (int) (Math.random() * cityList.size());
		Area city = cityList.get(index);
		String result = city.getName();
		//判断是否重复
		if(isUnique()){
			return checkUnique(result);
		}
		return result;
	}

}
