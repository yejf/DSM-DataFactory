/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.df.cfg.constants.StringConstants;
import com.df.cfg.impl.DataFactoryImpl;

/**
 * DataFactoryBuilder负责读到 datafactory.xml 配置文件或者 datafactory.properties 文件，
 * 并存放好这些信息，然后创建 DataFactory实例<br/>
 * <pre>
 * 使用者只需要按如下方试来使用：
 * 1.先创建 DataFactoryBuilder对象，再调用 configure方法
 * DataFactoryBuilder builder = new DataFactoryBuilder();
 * builder.configure();
 * 或者连在一起写：
 * DataFactoryBuilder builder = new DataFactoryBuilder().configure();
 * 2.再通过 builder 来创建DataFactory实例
 * DataFactory factory = builder.buildDataFactory();
 * 3.通过 factory的 getData或getUniqueData方法来获取模拟的数据
 * List<Entity> entityList = factory.getData(Entity.class);
 * 或
 * Entity entity = factory.getUniqueData(Entity.class);
 * 
 * </pre>
 * <b>前提注意事项</b>
 * <pre>
 *      在测试源文件夹下，要先创建 datafactory.xml 文件， 如下：
 *      &lt;data-factory&gt;
 *      	 &lt;load file="xx.xx.xx.test.Entity.xml"/&gt;
 *      	 &lt;load file="需要产生模拟数据实体的测试数据配置文件.xml"/&gt;
 *       &lt;/data-factory&gt;
 *      
 *      其实，在测试源文件夹，可以在任意包中创建测试数据配置文件 test.xxx.xml 文件，如下：
 *       &lt;class name="xxx.xx.xx.Entity" item="指定创建对象的个数"&gt;
 *      	 &lt;property name="属性名" generator="生成器[内置有15种]"/&gt;
 *      	 &lt;property name="age" generator="integer" min="15" max="28"/&gt;
 *      	 &lt;property name="hobby" generator="array" value="可以指定多个爱好，以,号分隔"/&gt;
 *      	 &lt;property name="gender" generator="enum" type="目标枚举的全限定名"/&gt;
 *      	....
 *       &lt;/class&gt;
 * </pre>
 * <pre>
 * 目前本组件提供了内置的15种生成策略，详见 com.df.generate包下的生成策略实现
 * </pre>
 * @see DataFactoryImpl
 * @author yejf
 * @date 2014-3-7 上午11:17:48
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class DataFactoryBuilder {

	/**********
	 * 默认配置文件路径
	 */
	private String default_path = "datafactory.xml";
	//其它的属性
	/** 存放需要加载的目标生成策略数据的文件,也是采用 xml 来配置 */
	private List<String> targetFiles = new ArrayList<String>();	
	
	/*********
	 * <pre>
	 * 使用Map来存放读取的整个XML中的配置信息
	 * 其中
	 * Key 是 类的全限定名, 如： com.xx.Student
	 * Value 是有关此类的所有属性配置信息，它又是一个Map, 这个Map中将存放所属性对应的所有配置信息
	 * 如：
	 * Map tmp = new HashMap();
	 * Properties props = new Properties();
	 * props.setProperty("generator","integer");
	 * props.setProperty("min","5");
	 * props.setProperty("max","10");
	 * tmp.put("age",props1)
	 * ...
	 * Properties props2 = new Properties();
	 * props.setProperty("generator","email");
	 * tmp.put("email",props2);
	 * 
	 * ...
	 * map.put("xx.x.Student",tmp);
	 * </pre>
	 */
	private Map<String, Map<String,Properties>> map = new HashMap<String, Map<String,Properties>>();
	
	/***
	 * 此Map存放的是类名与 生成条目数，如：
	 * String name = "xx.xx.Student"
	 * Integer item = 10000;
	 * itemMap.put(name,item);
	 */
	private Map<String, Integer> itemMap = new HashMap<String, Integer>();
	
	
	/*********************
	 * 读取并解析配置文件<br/>
	 * 默认的配置文件名为 datafactory.xml， 存放在测试的根目录下
	 */
	public DataFactoryBuilder configure(){
		return configure(default_path);
	}
	
	/******************
	 * 读取并解析指定路径的配置文件
	 * @param path
	 */
	public DataFactoryBuilder configure(String path){
		//准备解析 目标xml文件
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			//使用 xsd 来验证目标文件是否合法
//			factory.setNamespaceAware(true);
//			factory.setValidating(true);
			//暂不加XSD 的验证功能
			
			//开始解析目标文件
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			Document doc = builder.parse(in);
			
			//获取根节点
			Element root = doc.getDocumentElement();
			//获取所有 load元素
//			NodeList nodeList = root.getElementsByTagName("load");
			NodeList nodeList = root.getElementsByTagName(StringConstants.DF_LOAD);
			for(int i=0;i<nodeList.getLength();i++){
				Element loadElement = (Element)nodeList.item(i);
				//获取它的属性
//				String file = loadElement.getAttribute("file");
				String file = loadElement.getAttribute(StringConstants.DF_FILE);
				//添加到集合中
				targetFiles.add(file);
			}
			in.close(); //关闭资源
			
			//紧接着，开始去加载每一个 类属性生成策略文件
			for(String target : targetFiles){
				in = Thread.currentThread().getContextClassLoader().getResourceAsStream(target);
				//开始解析
				doc = builder.parse(in);
				//处理
				Element classRoot = doc.getDocumentElement();
				//得到类名与item值
//				String className = classRoot.getAttribute("name");
				String className = classRoot.getAttribute(StringConstants.TEST_XML_NAME);
//				int item = Integer.parseInt(classRoot.getAttribute("item"));
				int item = Integer.parseInt(classRoot.getAttribute(StringConstants.TEST_XML_ITEM));
				//添加到 itemMap中
				itemMap.put(className, item);
				
				//创建一个 Map来存放下面要解析的数据
				Map<String, Properties> tempMap = new HashMap<String, Properties>();
				
				//接下来解析属性 相关信息
//				NodeList propList = classRoot.getElementsByTagName("property");
				NodeList propList = classRoot.getElementsByTagName(StringConstants.TEST_XML_PROPERTY);
				for(int i = 0;i<propList.getLength();i++){
					Element propElement = (Element)propList.item(i);
					//取属性
//					String propName = propElement.getAttribute("name");  //属性名, 它是做为 tempMap的key 值
					String propName = propElement.getAttribute(StringConstants.TEST_XML_NAME);  //属性名, 它是做为 tempMap的key 值
					//以下所有属性都将存放在 Properties 中
//					String generator = propElement.getAttribute("generator");	 //取生成策略器的名字
					String generator = propElement.getAttribute(StringConstants.TEST_XML_GENERATOR);	 //取生成策略器的名字
					//以下属性有可能会没有设置，如果没有设置，则返回 empty string
//					String type = propElement.getAttribute("type"); 	//主要是针对 EnumGenerator 而设的
					String type = propElement.getAttribute(StringConstants.TEST_XML_TYPE); 	//主要是针对 EnumGenerator 而设的
//					String min = propElement.getAttribute("min");	//主要是针对数字或日期类型 设定的范围下限
					String min = propElement.getAttribute(StringConstants.TEST_XML_MIN);	//主要是针对数字或日期类型 设定的范围下限
//					String max = propElement.getAttribute("max"); //主要是针对数字或日期类型 设定的范围上限
					String max = propElement.getAttribute(StringConstants.TEST_XML_MAX); //主要是针对数字或日期类型 设定的范围上限
//					String unique = propElement.getAttribute("unique"); //确定属性值是唯一或不唯一
					String unique = propElement.getAttribute(StringConstants.TEST_XML_UNIQUE); //确定属性值是唯一或不唯一
//					String value = propElement.getAttribute("value"); //主要是针对CommonString生成器而设置的属性，用户可以填任意以,号分隔的字符串
					String value = propElement.getAttribute(StringConstants.TEST_XML_VALUE); //主要是针对CommonString生成器而设置的属性，用户可以填任意以,号分隔的字符串
//					String length = propElement.getAttribute("length");// 主要是针对 Name 生成器而设定的属性，可以指定姓名是2个字或3个字
					String length = propElement.getAttribute(StringConstants.TEST_XML_LENGTH);// 主要是针对 Name 生成器而设定的属性，可以指定姓名是2个字或3个字
					String precision = propElement.getAttribute(StringConstants.TEST_XML_PRECISION);// 主要是针对 double 生成器而设定的属性，可以指定浮点数的精度
					String prefix = propElement.getAttribute(StringConstants.TEST_XML_PREFIX); //主要是针对englishname生成器而设定的属性
					String suffix = propElement.getAttribute(StringConstants.TEST_XML_SUFFIX); //主要是针对englishname生成器而设定的属性
					//..
					//创建Properties 来存放以上属性
					Properties props = new Properties();
//					props.setProperty("generator", generator);
					props.setProperty(StringConstants.TEST_XML_GENERATOR, generator);
//					props.setProperty("type", type);
					props.setProperty(StringConstants.TEST_XML_TYPE, type);
//					props.setProperty("min", min);
					props.setProperty(StringConstants.TEST_XML_MIN, min);
//					props.setProperty("max", max);
					props.setProperty(StringConstants.TEST_XML_MAX, max);
//					props.setProperty("unique", unique);
					props.setProperty(StringConstants.TEST_XML_UNIQUE, unique);
//					props.setProperty("value", value);
					props.setProperty(StringConstants.TEST_XML_VALUE, value);
//					props.setProperty("length", length);
					props.setProperty(StringConstants.TEST_XML_LENGTH, length);
					props.setProperty(StringConstants.TEST_XML_PRECISION, precision);
					props.setProperty(StringConstants.TEST_XML_PREFIX, prefix);
					props.setProperty(StringConstants.TEST_XML_SUFFIX, suffix);
					
					//把 属性与它的所有配置信息都添加到tempMap中
					tempMap.put(propName, props);
				}
				//到此，所有的属性都添加完了
				map.put(className, tempMap);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
	}
	
	/******************
	 * 创建DataFactory实例，目前返回 DataFactoryImpl的实例
	 * @return
	 */
	public DataFactory buildDataFactory(){
		//创建 DataFactory ,并把读取的数据做为参数传递给 DataFactory
		DataFactory factory = new DataFactoryImpl(itemMap, map);
		return factory;
	}

	/*****************
	 * 获取类与类中所有属性的配置映射关系
	 * @return
	 */
	public Map<String, Map<String, Properties>> getMap() {
		return map;
	}

	/**************
	 * 获取类与实例条目的映射关系
	 * @return
	 */
	public Map<String, Integer> getItemMap() {
		return itemMap;
	}
	
}

