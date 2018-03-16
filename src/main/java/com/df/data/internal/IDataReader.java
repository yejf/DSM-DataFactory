/**************************
 * 
 */
package com.df.data.internal;

import java.util.List;

import com.df.data.Data;

/************************
 * 
 * @author yejf
 * @date 2014-3-4 下午3:34:43
 * @since JDK6.0
 * @version 1.0
 * @description 数据抽取接口，它定义了如何从底层来获取数据，一般采用XML格式来获取，当然，也要以通过DB来获取
 */
public interface IDataReader<T extends Data> {

	/***********************
	 * 读取数据的方法,采用默认路径
	 * 
	 * @return 数据的集合
	 */
	List<T> read();
	
	/******************************
	 * 读取数据的方法，可以指定数据的路径
	 * @param path 数据文件的路径
	 * @return 数据的集合
	 */
	List<T> read(String path);
	
}
