/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.data.internal;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.df.data.Surname;

/**
 * @author yejf
 * @date 2014-3-5 上午9:22:13
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class TestSurnameReader {

	@Test
	@Ignore
	public void testRead(){
		IDataReader<Surname> idr = new SurnameReader();
		List<Surname> surnameList = idr.read();
		System.out.println("总姓氏条目："+surnameList.size());
		for(Surname surname : surnameList){
			System.out.println(surname);
		}
	}
}
