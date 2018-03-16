/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.Collections;
import java.util.List;

import com.df.data.Surname;
import com.df.data.UniversalCharacter;
import com.df.data.cache.BaseDataReadCache;
import com.df.generate.AbstractGenerator;

/**
 * 中文名字生成器
 * 它依整于底层的数据抽取层来获取基础数据
 * @author yejf
 * @date 2014-3-5 下午2:47:02
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class NameGenerator extends AbstractGenerator {
	
	private String current; //当前的姓氏
	private int max; //此姓氏最大的姓名个数
	private int count; //计数器，以决定是否要更换姓氏后再产生
	
	private List<UniversalCharacter> usedList;  //需要使用的通用汉字
	
	public NameGenerator() {
		super();
		setUnique(true); //默认是唯一的
		init();
	}

	/****************
	 * 初始化基本参数
	 */
	private void init(){
		//先初始化当前姓氏
		List<Surname> first = BaseDataReadCache.getSubListForSurname(0, 40);
		Surname surname = first.get((int)(Math.random()*first.size()));
		this.current = surname.getName();
		log.info("当前的姓氏值是："+current);
		//可以根据 姓氏(surname)中的seq属性来生成 max值，seq越小，对应的max就越大
		calcMax(surname);
		this.count = 0;
		//加载需要使用的通用汉字
//		usedList = BaseDataReadCache.getUniversalCharactersExceptLevel3();
		usedList = BaseDataReadCache.getUniversalCharactersByLevel(1);
		log.info("目前要使用的通用汉字个数为："+usedList.size());
	}
	
	/***************
	 * 根据姓氏中的seq[排序]来计算出此姓氏可以产生的名字的最大个数
	 * @param surname
	 * @return
	 */
	private void calcMax(Surname surname) {
		// TODO Auto-generated method stub
		max = (int)((100 - surname.getSeq())*0.2);
		//判断
		if(max < 1){
			max = 1;	//如果此姓氏的排名是低于95的，则 (100 - 95) * 0.2 = 1, 也就是说，生成的结果是低于1的，则max置为1，也就是此姓氏使用单 次
		}
		log.info("当前的max值是："+max);
	}

	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		//判断当前姓氏的使用次数是否已到
		if(this.count >= this.max){
			//重新生成当前姓氏
			generateCurrent();
			//重置count
			this.count = 0;
		}
		//使用StringBuilder来存放姓与名
		StringBuilder builder = new StringBuilder();
		builder.append(this.current);
		//产生名 [只使用前2级通用汉字]
		Collections.shuffle(usedList);
		//取出其中的两个
		int index = (int)(Math.random()*usedList.size());
		//判断 length, 以确定是否是随机
		int len = 0;
		if(getLength() == null){
			//那就随机生成 1 或 2
			len = ((int)(Math.random()*10) + 1)  % 2 + 1;
		}else{
			len = Integer.parseInt(getLength());
		}
		//判断，以防止越界
		if(index == usedList.size() - 1)
			index -= 1;
		//根据 length 来取
		List<UniversalCharacter> subList = usedList.subList(index, index + len);
		//与姓名拼接在一起
		for(UniversalCharacter uc : subList){
			builder.append(uc.getCharacter());
		}
		this.count ++;  //每产生一个，计数就增1
		//最后，判断此名字是否已存在
		if(isUnique()){
//			this.count ++;  
			return checkUnique(builder.toString());
		}
		//如果允许重复的话，就直接返回
		return builder.toString();
	}

	/****************
	 * 重新产生新的姓氏
	 */
	private void generateCurrent() {
		// TODO Auto-generated method stub
		log.info("重新产生姓氏....");
		List<Surname> sList = BaseDataReadCache.getSurnameList();
		Surname surname = sList.get((int)(Math.random()*sList.size()));
		this.current = surname.getName();
		//计算max
		calcMax(surname);
	}

}
