/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.util;

/**
 * @author yejf
 * @date 2014-3-6 下午4:56:33
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class NumberUtil {

	private static final int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, };
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	/************************
	 * 根据长度产生数字序列
	 * 
	 * @param length
	 * @return
	 */
	public static String genSequence(int length) {
		// 先创建 StringBuilder
		StringBuilder builder = new StringBuilder();
		// 依据 numbers数组来生成一个6位的身份证号码前缀
		for (int i = 0; i < length; i++) {
			int idx = (int) (Math.random() * chars.length);
			builder.append(chars[idx]);
		}
		// 返回
		return builder.toString();
	}

	/*********************
	 * 本方法主要是为身份证前6位服务
	 * 
	 * @return
	 */
	public static String genIdCard6Bits() {
		// 先创建 StringBuilder
		StringBuilder builder = new StringBuilder();
		// 依据 numbers数组来生成一个6位的身份证号码前缀
		for (int i = 0; i < 6; i++) {
			int idx = (int) (Math.random() * numbers.length);
			builder.append(numbers[idx]);
		}
		// 返回
		return builder.toString();
	}

	/*************
	 * 本方法主要是产生身份证后面4位的
	 * 
	 * @return
	 */
	public static String genIdCard4Bits() {
		// 先创建 StringBuilder
		StringBuilder builder = new StringBuilder();
		// 依据 numbers数组来生成一个6位的身份证号码前缀
		for (int i = 0; i < 3; i++) {
			int idx = (int) (Math.random() * chars.length);
			builder.append(chars[idx]);
		}
		// 最后一位可以是 X,但是要保证x出现的较少一点
		int last = (int) (Math.random() * chars.length);
		if (last == 5) {
			builder.append('X');
		} else {
			builder.append(chars[last]);
		}
		return builder.toString();
	}
}
