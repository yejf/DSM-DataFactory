package com.df.util;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

public class TestDateUtil {

	@Test
	@Ignore
	public void testDate(){
		Date d = DateUtil.buildDate(1998, 1, 1, 12, 24, 56);
		Date d2 = DateUtil.buildDate(2008, 8, 8);
		
		System.out.println(DateUtil.toString(d,"yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtil.toString(d2,"yyyy-MM-dd HH:mm:ss"));
		
		System.out.println(DateUtil.getMonth(d));
		System.out.println(DateUtil.getHour(d2));
		
		System.out.println(DateUtil.toString(d2, "yyyyMMdd"));
	}
}
