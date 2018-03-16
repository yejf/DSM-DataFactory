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

import com.df.data.UniversalCharacter;

/**
 * @author yejf
 * @date 2014-3-5 上午9:25:34
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class UniversalCharacterReader implements
		IDataReader<UniversalCharacter> {
	
	private static final Logger log = Logger.getLogger(UniversalCharacterReader.class);

	private String path = "uc.xml";

	public List<UniversalCharacter> read() {
		// TODO Auto-generated method stub
		log.debug("正在准备加载文件流....");
		InputStream in = SurnameReader.class.getResourceAsStream(path);
		if (in != null) {
			return read(in);
		} else {
//			System.out.println("文件不存在....");
			log.error("文件不存在....");
			return null;
		}
	}

	/************
	 * 读取数据
	 * 
	 * @param in
	 * @return
	 */
	private List<UniversalCharacter> read(InputStream in) {
		// TODO Auto-generated method stub
		List<UniversalCharacter> ucList = new ArrayList<UniversalCharacter>();
		// 构建DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 解析目标XML文档
			Document doc = builder.parse(in);
			// 处理此 doc 文档
			Element root = doc.getDocumentElement();
			// 获取所有的一级子节点
			log.debug("正在获取所有的 character 节点元素");
			NodeList levelList = root.getElementsByTagName("character");
			log.debug("获取character节点完成，共计："+levelList.getLength());
			//遍历
			for(int i = 0;i<levelList.getLength();i++){
				Element levelElement = (Element)levelList.item(i);
				//获取level的值
				int level = Integer.parseInt(levelElement.getAttribute("level"));
				//
//				System.out.println("当前正在读取的通用汉字级别是："+level);
				log.debug("当前正在读取的通用汉字级别是："+level);
				//再获取此level下的所有子节点
				NodeList cList = levelElement.getElementsByTagName("c");
//				System.out.println("当前子元素有："+cList.getLength());
				log.debug("当前子元素有："+cList.getLength());
				//循 环处理
				log.debug("正在把节点的属性转换成对象");
				for(int j = 0; j < cList.getLength(); j++){
					//得到每一个 c 节点
					Element cElement = (Element)cList.item(j);
					//转换
					UniversalCharacter uc = convert(cElement, level);
					//把实例添加到集合中
					ucList.add(uc);
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ucList;
	}

	/********************
	 * 把每个c节点转换成 UniversalCharacter实例
	 * @param cElement
	 * @param level
	 * @return
	 */
	private UniversalCharacter convert(Element cElement, int level) {
		// TODO Auto-generated method stub
		UniversalCharacter uc = new UniversalCharacter();
		//设置级别
		uc.setLevel(level);
		//设置通用汉字的其它值
		uc.setCode(cElement.getAttribute("code"));
		uc.setStroke(Integer.parseInt(cElement.getAttribute("stroke")));
		uc.setCharacter(cElement.getAttribute("value"));
		//处理其它属性
		//...
		return uc;
	}

	public List<UniversalCharacter> read(String path) {
		// TODO Auto-generated method stub
		// 获取指定文件的输入流
		File file = new File(path);
		if (!file.exists()) {
//			System.out.println("文件不存在...." + file.getAbsolutePath());
			log.error("文件不存在...." + file.getAbsolutePath());
			return null;
		}
		try {
			InputStream in = new FileInputStream(file);
			return read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("加载资源文件失败", e);
		}
		return null;
	}

}
