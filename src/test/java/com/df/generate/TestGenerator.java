package com.df.generate;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

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
import com.df.util.DateUtil;

public class TestGenerator {

	@Test
	public void testInt(){
//		IDataGenerator idg = new IntegerGenerator();
//		IDataGenerator idg = new IntegerGenerator(true,-15,28);
		AbstractGenerator idg = new IntegerGenerator();
		idg.setMin("-15");
		idg.setMax("28");
		for(int i= 0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testDouble(){
//		IDataGenerator idg = new DoubleGenerator(true,-2.2,5.6,2);
		AbstractGenerator idg = new DoubleGenerator();
		/*idg.setMin("-2.2");
		idg.setMax("6.2");*/
		idg.setPrecision("2");
		for(int i= 0;i<10 ;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testName(){
//		IDataGenerator idg = new NameGenerator(true,1);
		AbstractGenerator idg = new NameGenerator();
		idg.setLength("1");
		for(int i = 0;i<500;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testAddress(){
//		AddressGenerator idg = new AddressGenerator();
		AbstractGenerator idg = new AddressGenerator();
//		idg.setUnique(true);
		for(int i = 0;i<100;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testProvince(){
//		ProvinceGenerator idg = new ProvinceGenerator();
		AbstractGenerator idg = new ProvinceGenerator();
//		idg.setUnique(true);
		for(int i = 0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	@Test
	public void testCity(){
//		CityGenerator idg = new CityGenerator();
		AbstractGenerator idg = new CityGenerator();
//		idg.setUnique(true);
		for(int i = 0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testCode(){
//		IDataGenerator idg = new CodeGenerator(false);
		AbstractGenerator idg = new CodeGenerator();
//		idg.setUnique(true);
		for(int i = 0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testDate(){
		/*Date start = DateUtil.buildDate(2010, 10, 1, 12, 0, 0);
		Date end = DateUtil.buildDate(2011,3,10,15,30,0);
		IDataGenerator idg = new DateGenerator(true,start,end);*/
//		IDataGenerator idg = new DateGenerator();
		AbstractGenerator idg = new DateGenerator();
		idg.setMin("2010-02-12");
		idg.setMax("2013-09-11");
		for(int i = 0;i<30;i++){
			Date tmp = (Date)idg.generate();
			System.out.println(DateUtil.toString(tmp, "yyyy-MM-dd HH:mm:ss"));
		}
	}
	
	@Test
	public void testIdCard(){
//		IDataGenerator idg = new IdCardGenerator();
		AbstractGenerator idg = new IdCardGenerator();
		for(int i=0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testEmail(){
//		IDataGenerator idg = new EmailGenerator();
		AbstractGenerator idg = new EmailGenerator();
		for(int i=0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testPhone(){
//		IDataGenerator idg = new PhoneGenerator();
		AbstractGenerator idg = new PhoneGenerator();
		for(int i =0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
	
	@Test
	public void testArray(){
//		IDataGenerator idg = new ArrayGenerator();
//		IDataGenerator idg = new ArrayGenerator("管理员,财务人员,工程师,美工");
		AbstractGenerator idg = new ArrayGenerator();
//		idg.setValue("管理员,财务人员,工程师,美工");
		for(int i = 0;i<10;i++){
			String[] str = (String[])idg.generate();
			System.out.println(Arrays.toString(str));
		}
	}
	
	@Test
	public void testCommonString(){
//		IDataGenerator idg = new CommonStringGenerator();
//		IDataGenerator idg = new CommonStringGenerator("张三,李四,王五,小二,王半时");
		AbstractGenerator idg = new CommonStringGenerator();
		idg.setValue("张三,李四,王五,小二,王半时");
		for(int i =0;i<20;i++){
			String str = (String)idg.generate();
			System.out.println(str);
		}
	}
	
	@Test
	public void testEnum(){
//		IDataGenerator idg = new EnumGenerator("com.df.generate.Season");
		AbstractGenerator idg = new EnumGenerator();
		idg.setType("com.df.generate.Season");
		for(int i=0;i<10;i++){
			Object name =  idg.generate();
			System.out.println(name);
			//把字符串转换成枚举
			/*Season s = Season.valueOf(name);
			System.out.println("枚举:"+s);*/
		}
	}
	
	@Test
	public void testEnglishName(){
		AbstractGenerator idg = new EnglishNameGenerator();
//		idg.setPrefix("true");
//		idg.setSuffix("true");
		for(int i = 0;i<30;i++){
			System.out.println(idg.generate());
		}
	}
}


