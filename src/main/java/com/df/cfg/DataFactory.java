/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.cfg;

import java.util.List;

/**
 * 数据工厂接口
 * @author yejf
 * @date 2014-3-7 上午11:25:39
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public interface DataFactory {

	/**********************
	 * 创建多个实例对象
	 * @param c
	 * @return
	 */
	public <T> List<T> getData(Class<T>  c);
	
	/***********************
	 * 创建单个的实例对象
	 * @param c
	 * @return
	 */
	public <T> T getUniqueData(Class<T> c);
	
	
}
