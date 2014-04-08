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

import com.df.data.Area;

/**
 * @author yejf
 * @date 2014-3-5 上午9:51:57
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class AreaReader implements IDataReader<Area> {

	private static final Logger log = Logger.getLogger(AreaReader.class);
	
	private String path = "area.xml";

	public List<Area> read() {
		// TODO Auto-generated method stub
		log.info("正在准备为目标文件创建流对象");
		InputStream in = SurnameReader.class.getResourceAsStream(path);
		if (in != null) {
			return read(in);
		} else {
			log.error("文件不存在");
//			System.out.println("文件不存在....");
			return null;
		}
	}

	/**************************
	 * 读取XML文件中的数据
	 * 
	 * @param in
	 * @return
	 */
	private List<Area> read(InputStream in) {
		// TODO Auto-generated method stub
		List<Area> areaList = new ArrayList<Area>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 解析目标XML文档
			Document doc = builder.parse(in);
			// 处理此 doc 文档
			Element root = doc.getDocumentElement();
			// 获取所有的一级子节点
			NodeList provinceNodes = root.getElementsByTagName("province");
//			System.out.println("全国共计："+provinceNodes.getLength()+"个省份.");
			log.debug("全国共计："+provinceNodes.getLength()+"个省份.");
			// 遍历
			log.debug("处理省份节点");
			for(int i=0;i<provinceNodes.getLength();i++){
				Element provinceElement = (Element)provinceNodes.item(i);
				log.debug("处理省份节点，把级别[level]定为1");
				int level = 1; //省份等级定为 1级， 城市 为 2级，区域为 3 级
				//获取省份名与代码
				String name = provinceElement.getAttribute("name");
				String code = provinceElement.getAttribute("code");
				//创建 Area 实例
				Area proArea = new Area();
				proArea.setName(name);
				proArea.setCode(code);
				proArea.setLevel(level);
				//处理城市 
				log.debug("处理城市节点");
				List<Area> cityAreaList = processCities(provinceElement);
				//把这些城市与当前的省份关联起来
//				proArea.setChildren(cityAreaList);
				for(Area city : cityAreaList){
					proArea.addChilden(city); //双向注册
				}
				//再把这些省份Area添加到集合中
				areaList.add(proArea);
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
		return areaList;
	}

	/****************************
	 * 根据省份元素来处理它下面的所有城市子元素
	 * @param provinceElement
	 * @return
	 */
	private List<Area> processCities(Element provinceElement) {
		// TODO Auto-generated method stub
		List<Area> cityList = new ArrayList<Area>();
		//获取所有 city 节点
		NodeList cityNodes = provinceElement.getElementsByTagName("city");
		//遍历
		for(int i = 0; i < cityNodes.getLength(); i++){
			Element cityElement = (Element)cityNodes.item(i);
			Area city = new Area();
			//添加属性
			city.setName(cityElement.getAttribute("name"));
			city.setCode(cityElement.getAttribute("code"));
			//城市级别为 2 级
			log.debug("处理城市节点，把级别[level]定为2");
			city.setLevel(2);
			
			//再处理区域，如果当前的city有区域的话
			log.debug("处理区域节点");
			List<Area> areaList = processArea(cityElement);
			//把此集合添加到当前city中，注册关系
//			city.setChildren(areaList);
			for(Area area : areaList){
				city.addChilden(area);
			}
			//添加到集合中
			cityList.add(city);
		}
		return cityList;
	}

	/******************
	 * 根据city节点来处理所有的area子节点
	 * 当然，需要注意的是，有可能这个city节点下面没有任何的子节点
	 * @param cityElement
	 * @return
	 */
	private List<Area> processArea(Element cityElement) {
		// TODO Auto-generated method stub
		List<Area> areaList = new ArrayList<Area>();
		//获取所有area子元点
		NodeList areaNodes = cityElement.getElementsByTagName("area");
		//迭代处理第3级 区域信息
		for(int j = 0; j < areaNodes.getLength(); j++){
			Element areaElement = (Element)areaNodes.item(j);
			//创建第三级 Area对象
			Area area = new Area();
			//绑定属性
			area.setName(areaElement.getAttribute("name"));
			area.setCode(areaElement.getAttribute("code"));
			log.debug("处理区域节点，把级别[level]定为3");
			area.setLevel(3);
			//添加到集合中
			areaList.add(area);
		}
		return areaList;
	}

	public List<Area> read(String path) {
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
