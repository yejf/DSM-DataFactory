/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.df.generate.AbstractGenerator;

/**
 * Email地址生成器
 * @author yejf
 * @date 2014-3-6 下午3:59:35
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class EmailGenerator extends AbstractGenerator {

	private static String[] suffix ; //邮箱后缀
	private static List<String> letters; //字符
	
	static{
		//初始化数据
		suffix = new String[]{
				"@gmail.com","@163.com","@hotmail.com","@126.com","@qq.com","@139.com",
				"@tom.com","@taobao.com","@baidu.com","@apache.org","@renren.com","@tianya.com",
				"@spring.org","@hibernate.org","@jboss.org","@ibm.com.cn","@msn.com","@csdn.com"
		};
		//
		letters = new ArrayList<String>();
		for(char c = 'a'; c <= 'z'; c++){
			letters.add(String.valueOf(c));
		}
	}
	/* (non-Javadoc)
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	public Object generate() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		//先获取后缀
		String back = suffix[(int)(Math.random()*suffix.length)];
		//再产生前缀
		int length = 2 + (int)(Math.random()*8); //2 ~ 12个字符
		Collections.shuffle(letters);
		List<String> tmp = letters.subList(0, length);
		for(String s : tmp){
			builder.append(s);
		}
		builder.append(back);
		//判断
		if(isUnique()){
			return checkUnique(builder.toString());
		}
		return builder.toString();
	}

}
