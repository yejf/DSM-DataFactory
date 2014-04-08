package com.df.generate;

/*****************************
 * 数据产生接口，提供统一的方式来模拟数据.
 * 本接口有各种实现，用户也可以扩展
 * 实现有：
 * StringGenerator
 * NameGenerator
 * AddressGenerator
 * ProvinceGenerator
 * CityGenerator
 * AreaGenerator
 * DateGenerator
 * IntegerGenerator
 * DoubleGenerator
 * FloatGenerator
 * ArrayGenerator
 * EnumGenerator
 * ....
 * @author yejf
 * @date 2014-3-5 上午11:56:10
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public interface IDataGenerator {

	/**************************
	 * 产生数据，这个数据可以有基本类型、可以指定数据范围、可以是数组、也可以是枚举等,
	 * 由具体的实现类来实现
	 * @return
	 */
	Object generate();
	
}

