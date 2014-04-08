/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 * 
 * @author yejf
 * @date 2014-3-6 下午4:05:32
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class DateUtil {

	/****************
	 * 根据年、月、日来构建一个日期
	 * 
	 * @param y
	 * @param m
	 *            从1至12
	 * @param d
	 * @return
	 */
	public static Date buildDate(int y, int m, int d) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m - 1);
		cal.set(Calendar.DAY_OF_MONTH, d);
		return cal.getTime();
	}

	/****************
	 * 依年月日、时分秒 来构建日期对象
	 * 
	 * @param y
	 * @param m
	 * @param d
	 * @param h
	 * @param mm
	 * @param s
	 * @return
	 */
	public static Date buildDate(int y, int m, int d, int h, int mm, int s) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m - 1);
		cal.set(Calendar.DAY_OF_MONTH, d);
		cal.set(Calendar.HOUR_OF_DAY, h);
		cal.set(Calendar.MINUTE, mm);
		cal.set(Calendar.SECOND, s);
		return cal.getTime();
	}

	/*********************
	 * 把字符串直接解析成日期，本方法的模式是 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date buildDate(String strDate) {
		final String pattern = "yyyy-MM-dd";
		return buildDate(strDate, pattern);
	}

	/******************
	 * 指定模式来解析字符串为日期 类型
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date buildDate(String strDate, String pattern) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*****************
	 * 把日期 格式化成 字符串，默认格式是 yyyy-MM-dd
	 * 
	 * @param d
	 * @return
	 */
	public static String toString(Date d) {
		final String pattern = "yyyy-MM-dd";
		return toString(d, pattern);
	}

	/******************
	 * 安指定模式来格式化日期
	 * 
	 * @param d
	 * @param pattern
	 * @return
	 */
	public static String toString(Date d, String pattern) {
		// TODO Auto-generated method stu
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

	/*******
	 * 取日期的年份
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		return get(d, Calendar.YEAR);
	}

	public static int getMonth(Date d) {
		return get(d, Calendar.MONTH) + 1;
	}

	public static int getDay(Date d) {
		return get(d, Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date d) {
		return get(d, Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date d) {
		return get(d, Calendar.MINUTE);
	}

	public static int getSecond(Date d) {
		return get(d, Calendar.SECOND);
	}

	private static int get(Date d, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(field);
	}
}
