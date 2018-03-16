/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.cfg.constants;

/**
 * 字符串常量类，为设置属性与取属性而准备
 * @author yejf
 * @date 2014-3-10 上午11:29:49
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class StringConstants {

	/** 在datafactory.xml文件中指定的XML属性名 [必填]*/
	public static final String DF_LOAD = "load";
	public static final String DF_FILE = "file";
	
	/** 在 test.xxx.xml 文件中指定的XML属性名 及　元素名 */
	
	/**test.xxx.xml文件中class元素或property元素中 name属性 [必填]*/
	public static final String TEST_XML_NAME = "name";
	
	/**test.xxx.xml文件中 class元素中的 item 属性 [可选]*/
	public static final String TEST_XML_ITEM = "item";
	
	/** test.xxx.xml文件中 property　子元素 [必填]*/
	public static final String TEST_XML_PROPERTY = "property";
	
	/** test.xxx.xml文件中 property 元素中 generator属性 [必填]*/
	public static final String TEST_XML_GENERATOR = "generator";
	
	/** test.xxx.xml文件中 property 元素中 type属性 [可选]*/
	public static final String TEST_XML_TYPE = "type";
	
	/** test.xxx.xml文件中 property 元素中 min属性 [可选]*/
	public static final String TEST_XML_MIN = "min";
	
	/** test.xxx.xml文件中 property 元素中 max属性[可选]*/
	public static final String TEST_XML_MAX = "max";
	
	/** test.xxx.xml文件中 property 元素中 unique属性 [可选]*/
	public static final String TEST_XML_UNIQUE = "unique";
	
	/** test.xxx.xml文件中 property 元素中 value属性[可选]*/
	public static final String TEST_XML_VALUE = "value";
	
	/** test.xxx.xml文件中 property 元素中 length属性 [可选]*/
	public static final String TEST_XML_LENGTH = "length";
	
	/** test.xxx.xml文件中 property 元素中 precision 属性，主要是针对double生成策略的配置，[可选] */
	public static final String TEST_XML_PRECISION = "precision";

	/** test.xxx.xml文件中 property 元素中的 suffix属性，主要是针对EnglishName生成策略的配置 ， [可选 ] */
	public static final String TEST_XML_SUFFIX = "suffix";
	
	/** test.xxx.xml文件中 property 元素中的 prefix属性，主要是针对EnglishName生成策略的配置 ， [可选 ] */
	public static final String TEST_XML_PREFIX = "prefix";
	
}
