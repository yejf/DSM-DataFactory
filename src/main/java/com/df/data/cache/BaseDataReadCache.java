/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.data.cache;

import java.util.ArrayList;
import java.util.List;

import com.df.data.Area;
import com.df.data.Surname;
import com.df.data.UniversalCharacter;
import com.df.data.internal.AreaReader;
import com.df.data.internal.IDataReader;
import com.df.data.internal.SurnameReader;
import com.df.data.internal.UniversalCharacterReader;

/**
 * 本类提供一个底层数据只读缓存，为上面提供基础数据时只需要加载单次，避免多次重复加载
 * @author yejf
 * @date 2014-3-5 下午3:08:05
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class BaseDataReadCache {

	//初始化数据读取实例
	private static IDataReader<Surname> snReader = new SurnameReader();
	private static IDataReader<UniversalCharacter> ucReader = new UniversalCharacterReader();
	private static IDataReader<Area> areaReader = new AreaReader();
	//
	private static final List<Surname> surnameList;
	private static final List<UniversalCharacter> ucList;
	private static final List<Area> areaList ;
	
	//使用静态代码块来初始化这些数据集合
	static {
		surnameList = snReader.read();
		ucList = ucReader.read();
		areaList = areaReader.read();
	}
	
	/*****************
	 * 获取所有姓氏
	 * @return
	 */
	public static List<Surname> getSurnameList(){
		return surnameList;
	}

	/***************************
	 * 只取出指定位置处的姓氏
	 * @param start 起始位置
	 * @param end 结束位置
	 * @return
	 */
	public static List<Surname> getSubListForSurname(int start, int end){
		return surnameList.subList(start, end);
	}
	
	/********************
	 * 获取所有通用汉字
	 * @return
	 */
	public static List<UniversalCharacter> getUniversalCharacterList(){
		return ucList;
	}
	
	/*****************************
	 * 根据级别来获取通用汉字
	 * @param level， 此级别共有3级, 它的值为 1或2或3
	 * @return
	 */
	public static List<UniversalCharacter> getUniversalCharactersByLevel(int level){
		List<UniversalCharacter> subList = new ArrayList<UniversalCharacter>();
		//遍历
		for(UniversalCharacter uc : ucList){
			if(uc.getLevel() == level){
				subList.add(uc);
			}
		}
		return subList;
	}
	
	/*****************
	 * 找出1级与2级通用汉字
	 * @return
	 */
	public static List<UniversalCharacter> getUniversalCharactersExceptLevel3(){
		List<UniversalCharacter> subList = new ArrayList<UniversalCharacter>();
		//遍历
		for(UniversalCharacter uc : ucList){
			if(uc.getLevel() == 3){
				continue;
			}
			subList.add(uc);
		}
		return subList;
	}
	/************************
	 * 获取所有区域信息
	 * @return
	 */
	public static List<Area> getAreaList(){
		return areaList;
	}
	
	/*************************
	 * 根据省份名来获取此省份下的所有区域
	 * @param province
	 * @return
	 */
	public static Area getAreaByProvince(String province){
		for(Area area : areaList){
			if(area.getName().equals(province)){
				return area;
			}
		}
		return null;
	}
	
	/*****************************
	 * 依据省市来定位到市
	 * @param province
	 * @param city
	 * @return
	 */
	public static Area getAreaByCity(String province,String city){
		for(Area area : areaList){
			if(area.getName().equals(province)){
				//再迭代此省份下的所有市
				for(Area citys : area.getChildren()){
					if(citys.getName().equals(city)){
						return citys;
					}
				}
			}
		}
		return null;
	}
}
