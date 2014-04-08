/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.ArrayList;
import java.util.List;

import com.df.data.Area;

/**
 * 邮编生成器
 * @author yejf
 * @date 2014-3-5 下午11:10:15
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class CodeGenerator extends AddressGenerator {

	private List<String> codeList;
	
	public CodeGenerator() {
		super();
		// TODO Auto-generated constructor stub
		prepared();
	}

	/***********
	 * 从areaList中准备好所有的区域数据，从而为生成邮编准备
	 */
	private void prepared() {
		// TODO Auto-generated method stub
		if(codeList == null)
			codeList = new ArrayList<String>();
		for(Area area : areaList){
			//把省份中的code取出来，添加到集合中 [省份邮编不加入]
//			codeList.add(area.getCode());
			//处理第二级：城市
			List<Area> cityList = area.getChildren();
			//迭代
			for(Area city : cityList){
				codeList.add(city.getCode());
				//处理第三级，区域, 如果有的话
				List<Area> subList = city.getChildren();
				if(subList != null){
					for(Area sub : subList){
						codeList.add(sub.getCode());
					}
				}
			}
		}
	}

	@Override
	public Object generate() {
		// TODO Auto-generated method stub
		//获取所有的区域信息
		int index = (int)(Math.random() * codeList.size());
		String result = codeList.get(index);
		//判断 是否排重
		if(isUnique()){
			return checkUnique(result);
		}
		return result;
	}
}
