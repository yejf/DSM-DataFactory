/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.cfg.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.df.cfg.DataFactory;
import com.df.cfg.constants.GeneratorConstants;
import com.df.cfg.constants.StringConstants;
import com.df.generate.AbstractGenerator;
import com.df.generate.impl.AddressGenerator;
import com.df.generate.impl.ArrayGenerator;
import com.df.generate.impl.CityGenerator;
import com.df.generate.impl.CodeGenerator;
import com.df.generate.impl.CommonStringGenerator;
import com.df.generate.impl.DateGenerator;
import com.df.generate.impl.DoubleGenerator;
import com.df.generate.impl.EmailGenerator;
import com.df.generate.impl.EnglishNameGenerator;
import com.df.generate.impl.EnumGenerator;
import com.df.generate.impl.IdCardGenerator;
import com.df.generate.impl.IntegerGenerator;
import com.df.generate.impl.NameGenerator;
import com.df.generate.impl.PhoneGenerator;
import com.df.generate.impl.ProvinceGenerator;

/**
 * 数据工厂类
 * 
 * @author yejf
 * @date 2014-3-7 上午10:22:04
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class DataFactoryImpl implements DataFactory {

	/** 日志记录器 */
	private static Logger log = Logger.getLogger(DataFactoryImpl.class);

	/** 存放整个数据生成器与生成策略之间的关系，也就是每一个生成策略都有对应的一个生成策略类 */
//	private static Map<String, AbstractGenerator> generatorMap;
	private static Map<String, Class<? extends AbstractGenerator>> generatorMap;

	// 初始化 generatorMap
	static {
//		generatorMap = new HashMap<String, AbstractGenerator>();
		generatorMap = new HashMap<String, Class<? extends AbstractGenerator>>();
		// 填入 generator字符串与对应的处理类之间提映射关系
		/*generatorMap.put(GeneratorConstants.ADDRESS, new AddressGenerator());
		generatorMap.put(GeneratorConstants.ARRAY, new ArrayGenerator());
		generatorMap.put(GeneratorConstants.CITY, new CityGenerator());
		generatorMap.put(GeneratorConstants.CODE, new CodeGenerator());
		generatorMap.put(GeneratorConstants.COMMON_STRING,
				new CommonStringGenerator());
		generatorMap.put(GeneratorConstants.DATE, new DateGenerator());
		generatorMap.put(GeneratorConstants.DOUBLE, new DoubleGenerator());
		generatorMap.put(GeneratorConstants.EMAIL, new EmailGenerator());
		generatorMap.put(GeneratorConstants.ENUM, new EnumGenerator());
		generatorMap.put(GeneratorConstants.IDCARD, new IdCardGenerator());
		generatorMap.put(GeneratorConstants.INTEGER, new IntegerGenerator());
		generatorMap.put(GeneratorConstants.PHONE, new PhoneGenerator());
		generatorMap.put(GeneratorConstants.PHONE, new ProvinceGenerator());
		generatorMap.put(GeneratorConstants.SURNAME, new NameGenerator());*/

		// ...
		generatorMap.put(GeneratorConstants.ADDRESS,  AddressGenerator.class);
		generatorMap.put(GeneratorConstants.ARRAY, ArrayGenerator.class);
		generatorMap.put(GeneratorConstants.CITY,  CityGenerator.class);
		generatorMap.put(GeneratorConstants.CODE, CodeGenerator.class);
		generatorMap.put(GeneratorConstants.COMMON_STRING,
				CommonStringGenerator.class);
		generatorMap.put(GeneratorConstants.DATE, DateGenerator.class);
		generatorMap.put(GeneratorConstants.DOUBLE, DoubleGenerator.class);
		generatorMap.put(GeneratorConstants.EMAIL, EmailGenerator.class);
		generatorMap.put(GeneratorConstants.ENUM, EnumGenerator.class);
		generatorMap.put(GeneratorConstants.IDCARD,  IdCardGenerator.class);
		generatorMap.put(GeneratorConstants.INTEGER, IntegerGenerator.class);
		generatorMap.put(GeneratorConstants.PHONE, PhoneGenerator.class);
		generatorMap.put(GeneratorConstants.PROVINCE, ProvinceGenerator.class);
		generatorMap.put(GeneratorConstants.SURNAME, NameGenerator.class);
		generatorMap.put(GeneratorConstants.ENGLISH_NAME, EnglishNameGenerator.class);
		// ...
	}

	/** 存放类与生成条目信息 */
	private Map<String, Integer> itemMap;

	/** 存放类与各个属性的相关配置信息 */
	private Map<String, Map<String, Properties>> map;

	/****
	 * 构造方法
	 * 
	 * @param itemMap
	 * @param map
	 */
	public DataFactoryImpl(Map<String, Integer> itemMap,
			Map<String, Map<String, Properties>> map) {
		super();
		this.itemMap = itemMap;
		this.map = map;
	}

	/*****************************
	 * 返回多个对象实例
	 * 
	 * @param c
	 * @return
	 */
	public <T> List<T> getData(Class<T> c) {

		// 1.根据ItemMap 中的值来获取需要创建对象的个数
		String className = c.getName();
		log.debug("当前处理的类是：" + className);
		Integer item = itemMap.get(className);
		log.debug("需要产生的条目有：" + item + " 条");

		// 2. 根据 map中存放的类的属性的配置来生成模拟数据
		Map<String, Properties> propMap = map.get(className);
		//判断是否为空
		if(propMap == null){
			throw new RuntimeException("目标类没有配置: "+c.getName()+" , 请查看配置文件中是否有加入");
		}
		// 3. 获取属性到产生器的映射关系
		Map<String, AbstractGenerator> _p2gMap = getProperty2GeneratorMap(propMap);

		// 4. 根据_p2gMap与item 来创建目标对象
		return createTargetObjectList(_p2gMap, item, c);

	}

	/************************
	 * 根据属性与产生器的映射来生成属性的值，并根据item来决定创建对象的个数，c 为目标对象的类型
	 * 
	 * @param _p2gMap
	 *            属性与产生器之间的映射
	 * @param item
	 *            产生目标对象的条目
	 * @param c
	 *            目标对象的类型
	 * @return
	 */
	private <T> List<T> createTargetObjectList(
			Map<String, AbstractGenerator> _p2gMap, int item, Class<T> c) {
		// TODO Auto-generated method stub
		// 使用集合来存放最后的结果
		List<T> resultList = new ArrayList<T>();
		// 通过类名，使用反射来创建对象
		try {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < item; i++) {
				T object = c.newInstance();
				// 为此对象的指定属性添加值
				Iterator<String> propIter = _p2gMap.keySet().iterator();
				while (propIter.hasNext()) {
					// 清空builder
					builder.delete(0, builder.capacity());
					// 获取属性名
					String propName = propIter.next();
					// 获取属性对应的生成策略对象
					AbstractGenerator idg = _p2gMap.get(propName);
					// 回调此实例的 generate方法产生数据
					Object data = idg.generate();
					log.debug("\t---此属性的值是: " + data);
					// 通过反射来获取此属性的set方法
					builder.append("set")
							.append(propName.substring(0, 1).toUpperCase())
							.append(propName.substring(1));
					// 获取此属性的类型
					Class<?> propType = c.getDeclaredField(propName).getType();
					log.debug("当前的属性对应的方法名是：" + builder.toString() + " , 类型是："
							+ propType);
					Method setMethod = c.getDeclaredMethod(builder.toString(),
							new Class<?>[] { propType });
					// 通过反射方法调用，把值设置到属性中
					setMethod.invoke(object, data);
				}

				// 把此对象添加到集合中
				resultList.add(object);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("实例化失败.", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("不合法的访问", e);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("属性不存在", e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("安全异常", e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("方法不存在", e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("调用时有不合法的参数", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("调用时目标对象异常", e);
		}

		return resultList;
	}

	/**************
	 * 根据属性相应的配置，来生成对应的产生策略实例
	 * 
	 * @param propMap
	 *            属性相关配置的MAP
	 * @return 属性与生成策略实例的映射关系
	 */
	private Map<String, AbstractGenerator> getProperty2GeneratorMap(
			Map<String, Properties> propMap) {
		// TODO Auto-generated method stub
		// 定义一个Map来存放每个属性与它对应的生成器之间的映射关系
		Map<String, AbstractGenerator> _p2gMap = new HashMap<String, AbstractGenerator>();
		// 获取每个属性的生成策略，以生成数据
		Set<String> keySet = propMap.keySet();
		Iterator<String> iter = keySet.iterator();
		while (iter.hasNext()) {
			String propName = iter.next(); // 属性名
			// 此属性对应的配置
			Properties propValues = propMap.get(propName);
			// 取出所有的属性配置值
			String generator = propValues
					.getProperty(StringConstants.TEST_XML_GENERATOR); // 此属性值的产生策略器
			
			String min = propValues.getProperty(StringConstants.TEST_XML_MIN); // 此属性值的最小范围，有可能为
																				// ""
			String max = propValues.getProperty(StringConstants.TEST_XML_MAX); // 此属性值的最大范围，有可能为
																				// ""
			String unique = propValues
					.getProperty(StringConstants.TEST_XML_UNIQUE); // 此属性值是否唯一，有可能为
																	// ""
			String value = propValues
					.getProperty(StringConstants.TEST_XML_VALUE); // 此属性值来自于用户自定义配置的
																	// value 值 ,
																	// 有可能为 ""
			String length = propValues
					.getProperty(StringConstants.TEST_XML_LENGTH); // 只针对姓名属性值，它的长度可以是1或2，代表姓名是2字或3字
			
			String type = propValues.getProperty(StringConstants.TEST_XML_TYPE); // 只针对枚举类型，它的值是用户指定枚举的全限定名，有可能为
																					// ""
			String precision = propValues
					.getProperty(StringConstants.TEST_XML_PRECISION); // 只针对double，可以指定精度，
																		// 有可能为
																		// "";
			String prefix = propValues.getProperty(StringConstants.TEST_XML_PREFIX); 
			String suffix = propValues.getProperty(StringConstants.TEST_XML_SUFFIX);
			
			// 以上这些属性都是为创建 Generator时有可能需要的参数
			// 从 generatorMap中根据 generator值，选出 generator的类型，
			//再通过反射 来创建出此 generator的实例，然后设置相关的属性值，以保证每个属性都有与之对应的generator
			Class<? extends AbstractGenerator> generatorClass = generatorMap.get(generator);
			//创建实例
			AbstractGenerator _adg = null;
//			AbstractGenerator _adg = generatorMap.get(generator);
			try {
				_adg = generatorClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("创建生成器实例失败",e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("生成器访问失败", e);
			}
			
			if (_adg != null) {
				log.debug("当前的属性[" + propName + "] 使用产生器是："
						+ _adg.getClass().getName());
				// 设置相关的属性值，考虑到属性配置值并不是都会使用，所以，最一个判断后再设置值
				if (unique != null && unique.trim().length() > 0) {
					log.debug("\t---- unique: " + unique);
					_adg.setUnique(Boolean.parseBoolean(unique));
				}
				if (min != null && min.trim().length() > 0) {
					log.debug("\t---- min: " + min);
					_adg.setMin(min);
				}
				if (max != null && max.trim().length() > 0) {
					log.debug("\t---- max: " + max);
					_adg.setMax(max);
				}
				if (value != null && value.trim().length() > 0) {
					log.debug("\t---- value: " + value);
					_adg.setValue(value);
				}
				if (length != null && length.trim().length() > 0) {
					log.debug("\t---- length: " + length);
					_adg.setLength(length);
				}
				if (type != null && type.trim().length() > 0) {
					log.debug("\t---- type: " + type);
					_adg.setType(type);
				}
				if (precision != null && precision.trim().length() > 0) {
					log.debug("\t---- precision: " + precision);
					_adg.setPrecision(precision);
				}
				if(prefix != null && prefix.trim().length() > 0){
					log.debug("\t ---- prefix: "+prefix);
					_adg.setPrefix(prefix);
				}
				if(suffix != null && suffix.trim().length() > 0){
					log.debug("\t ---- suffix: "+suffix);
					_adg.setSuffix(suffix);
				}
				
				// 把属性与对应的 IDataGenerator 映射 起来
				_p2gMap.put(propName, _adg);

			} else {
				log.error("没有对应的数据生成器：" + generator);
				throw new RuntimeException("没有对应的数据生成器：" + generator);
			}
			//
		}
		return _p2gMap;
	}

	/*************************
	 * 返回单对象实例
	 * 
	 * @param c
	 * @param flag
	 * @return
	 */
	public <T> T getUniqueData(Class<T> c) {
		// TODO Auto-generated method stub
		// 1.根据ItemMap 中的值来获取需要创建对象的个数
		String className = c.getName();
		log.debug("当前处理的类是：" + className);
		int item = 1; //只产生一条
		// 2. 根据 map中存放的类的属性的配置来生成模拟数据
		Map<String, Properties> propMap = map.get(className);
		//判断是否为空
		if(propMap == null){
			throw new RuntimeException("目标类没有配置: "+c.getName()+" , 请查看配置文件中是否有加入");
		}
		// 3. 获取属性到产生器的映射关系
		Map<String, AbstractGenerator> _p2gMap = getProperty2GeneratorMap(propMap);

		// 4. 根据_p2gMap与item 来创建目标对象
		return createTargetObjectList(_p2gMap, item, c).get(0);
	}
}
