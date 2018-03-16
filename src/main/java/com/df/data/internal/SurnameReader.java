/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.data.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.df.data.Surname;

/**
 * @author yejf
 * @date 2014-3-4 下午3:52:26
 * @since JDK6.0
 * @version 1.0
 * @description 中国百家姓氏数据读取器
 */
public class SurnameReader implements IDataReader<Surname> {

	private static final Logger log = Logger.getLogger(SurnameReader.class);
	
	/** 指定需要读取的文件名，默认与读取器同处一个包下面  */
	private String path = "surname.xml";
	
	public List<Surname> read() {
		// TODO Auto-generated method stub
		log.info("正在把目标文件转换成流对象...");
		InputStream in = SurnameReader.class.getResourceAsStream(path);
		if(in != null) {
			log.debug("正在读取数据...");
			return read(in);
		} else {
//			System.out.println("文件不存在....");
			log.error("文件不存在....");
			return null;
		}
	}

	/***********
	 * 读取数据
	 * @param in
	 * @return
	 */
	private List<Surname> read(InputStream in) {
		// TODO Auto-generated method stub
		List<Surname> surnameList = new ArrayList<Surname>();
		//构建DocumentBuilderFactory
		log.info("构建 W3C DOM 工厂");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			log.info("构建 DOM Builder...");
			DocumentBuilder builder = factory.newDocumentBuilder();
			//解析目标XML文档
			log.info("正在解析XML文档");
			Document doc = builder.parse(in);
			//处理此 doc 文档
			Element root = doc.getDocumentElement();
			//获取所有的子节点
			NodeList fnList = root.getElementsByTagName("fn");
			log.debug("所有子节点有："+fnList.getLength());
			//处理子节点
			for(int i=0;i<fnList.getLength();i++){
				Element fnElement = (Element) fnList.item(i);
				//把 fnElement转换成 Surname对象
				Surname fn = convert(fnElement);
				//把surname实例添加到集合中
				surnameList.add(fn);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			log.error("解析配置失败",e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("解析失败",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("文件流失败",e);
		}
		return surnameList;
	}

	/***************
	 * 把元素解析成对象
	 * @param fnElement
	 * @return
	 */
	private Surname convert(Element fnElement) {
		// TODO Auto-generated method stub
		Surname surname = new Surname();
		//绑定属性
		surname.setSeq(Integer.parseInt(fnElement.getAttribute("seq")));
		surname.setName(fnElement.getAttribute("value"));
		//其它属性的处理
		//..
		return surname;
	}

	public List<Surname> read(String path) {
		// TODO Auto-generated method stub
		//获取指定文件的输入流
		File file = new File(path);
		if(!file.exists()){
//			System.out.println("文件不存在...."+file.getAbsolutePath());
			log.error("文件不存在...."+file.getAbsolutePath());
			return null;
		}
		try {
			InputStream in = new FileInputStream(file);
			return read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("读取文件流失败",e);
		}
		return null;
	}

}
