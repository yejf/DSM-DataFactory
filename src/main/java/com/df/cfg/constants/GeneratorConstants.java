/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.cfg.constants;

/**
 * 生成器常量字符串
 * @author yejf
 * @date 2014-3-10 下午12:23:02
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class GeneratorConstants {

	/** 地址生成器: 主要是由 省份、地区市、区县 三级所组成 */
	public static final String ADDRESS = "address";
	
	/** 数组生成器： 可以由用户指定数组的内容选项，如： 值1,值2,值3 */
	public static final String ARRAY = "array";
	
	/** 城市生成器： 主要是由地区市组成 */
	public static final String CITY = "city";
	
	/** 邮编生成器：由要是由区县的邮编生成 */
	public static final String CODE = "code";
	
	/** 通用字符串：可以由用户指定任意的字符串组合，然后从这些中随机生成，如：张三,李四,王五,小二 */
	public static final String COMMON_STRING = "commonstring";
	
	/** 日期生成器：可以指定日期范围 */
	public static final String DATE  = "date";
	
	/** 浮点数生成器： 可以指定范围 */
	public static final String DOUBLE = "double";
	
	/** 邮件生成器： 目前的后缀是内定的多个邮箱后缀中随机生成的 */
	public static final String EMAIL = "email";
	
	/** 枚举生成器： 可以由用户指定枚举类型来生成 */
	public static final String ENUM = "enum";
	
	/** 身份证生成器： 可以生成任意的身份证号码**/
	public static final String IDCARD = "idcard";
	
	/** 整数生成器： 可以指定范围 */
	public static final String INTEGER = "integer";
	
	/** 中文姓名生成器： 它由百家姓及1级通用汉字组成，可以指定length的大小来确定是双字或三字姓名 */
	public static final String SURNAME = "surname";
	
	/** 手机号码生成器：它由指定的前缀及任意数字组成**/
	public static final String PHONE = "phone";
	
	/** 省份生成器： 由中国所有省份来随机生成 **/
	public static final String PROVINCE = "province";
	
	/**  英文名称生成器 */
	public static final String ENGLISH_NAME = "englishname";
	
}
