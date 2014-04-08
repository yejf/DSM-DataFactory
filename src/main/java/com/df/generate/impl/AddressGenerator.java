/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.List;

import com.df.data.Area;
import com.df.data.cache.BaseDataReadCache;
import com.df.generate.AbstractGenerator;

/**
 * 地址生成器
 * @author yejf
 * @date 2014-3-5 下午5:23:28
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class AddressGenerator extends AbstractGenerator {

	protected static List<Area> areaList;
	private StringBuilder builder ;
	
	static {
		//初始化areaList
		areaList = BaseDataReadCache.getAreaList();
	}
	
	public AddressGenerator() {
		super();
		//构建好StringBuilder
		builder = new StringBuilder();
	}
	
	/* 
	 * 产生地址数据，它由 省份、城市、区域 三级组成
	 * 如：
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		//先清空builder
		builder.delete(0, builder.capacity());
		//随机从集合中产生一个省份
		int index = (int)(Math.random()*areaList.size());
		Area area = areaList.get(index);
		//加入省份
		builder.append(area.getName());
		//从它的子元素中随机选取一个城市
		List<Area> cityList = area.getChildren();
		index = (int)(Math.random()*cityList.size());
		Area city = cityList.get(index);
		//加入城市
		builder.append(city.getName());
		//判断是否有区域
		List<Area> subList = city.getChildren();
		if(subList !=null && subList.size() != 0){
			index = (int)(Math.random()*subList.size());
			Area sub = subList.get(index);
			//加入区域
			builder.append(sub.getName());
		}
		
		//判断是否重复
		if(isUnique()){
			return checkUnique(builder.toString());
		}
		return builder.toString();
	}

}
