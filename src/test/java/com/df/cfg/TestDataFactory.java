package com.df.cfg;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

import com.df.cfg.constants.StringConstants;
import com.df.entity.Book;
import com.df.entity.Student;

public class TestDataFactory {

	@Test
	@Ignore
	public void testDF(){
		DataFactoryBuilder builder = new DataFactoryBuilder();
		builder.configure(); //加载配置文件
		//构建数据工厂
//		DataFactory factory = builder.buildDataFactory();
		
		//查看获取的 itemMap 与 Map
		Map<String, Integer>itemMap = builder.getItemMap();
		Map<String, Map<String, Properties>> map = builder.getMap();
		
		//查看集合中的值是否正确
		Iterator<String> iter = itemMap.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			Integer value = itemMap.get(key);
			System.out.printf("%s => %d\n", key, value);
		}
		
		//
		Iterator<String> iter2 = map.keySet().iterator();
		while(iter2.hasNext()){
			String key = iter2.next();	//类名
			System.out.println("类："+key+" 下的所有配置是：");
			//再获取value   //此类下的所有属性配置
			Map<String, Properties> msp = map.get(key);
			Iterator<String> propIter = msp.keySet().iterator();
			while(propIter.hasNext()){
				String prop = propIter.next();
				Properties p = msp.get(prop);
				//依次获取每个属性
				//输出
				System.out.printf("\t属性：%s\n",prop);
				System.out.println("\t\t生成器："+p.getProperty(StringConstants.TEST_XML_GENERATOR));
				System.out.println("\t\t类型："+p.getProperty(StringConstants.TEST_XML_TYPE));
				System.out.println("\t\t最小值："+p.getProperty(StringConstants.TEST_XML_MIN));
				System.out.println("\t\t最大值："+p.getProperty(StringConstants.TEST_XML_MAX));
				System.out.println("\t\t通用值："+p.getProperty(StringConstants.TEST_XML_VALUE));
				System.out.println("\t\t长度："+p.getProperty(StringConstants.TEST_XML_LENGTH));
				System.out.println("\t\t唯一性："+p.getProperty(StringConstants.TEST_XML_UNIQUE));
			}
		}
		
//		List<Area> areaList = factory.getData(Area.class);
//		List<Surname> suList = factory.getData(Surname.class);
		
	}
	
	@Test
	public void testDFImpl(){
		DataFactoryBuilder builder = new DataFactoryBuilder().configure("aaa.xml");
//		builder.configure();
		DataFactory factory  = builder.buildDataFactory();
		/*
		List<Student> stuList = factory.getData(Student.class);
		System.out.println("当前生成的元素条目: "+stuList.size());
		for(Student stu : stuList){
			System.out.println(stu);
		}
		
		System.out.println("//================================");
		Student s = factory.getUniqueData(Student.class);
		System.out.println(s);
		System.out.println("//================================");*/
		
		List<Book> bookList = factory.getData(Book.class);
		System.out.println("--- Book条目有："+bookList.size());
		for(Book  b  : bookList){
			System.out.println(b);
		}
		
		System.out.println("//================================");
		
		List<Student> stuList = factory.getData(Student.class);
		System.out.println("--- Student条目有："+stuList.size());
		for(Student stu : stuList){
			System.out.println(stu);
		}
	}
}


