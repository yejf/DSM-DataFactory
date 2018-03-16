package com.df.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/****************************
 * 利用 W3C的 Transformer 来把DOM文档树写到本地文件中
 * 
 * @author yejf
 * 
 */
public class DomWriter {

	/***********************************
	 * 把给定的Document文件树持久化到指定文件中
	 * 
	 * @param doc
	 * @param targetFile
	 *            持久化指定的文件，如果此文件不存在，则会创建一个.
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 */
	public static void persistDocument(Document doc, String targetFile)
			throws Exception {
		File file = new File(targetFile);
		if (file.isDirectory()) {
			System.err.println("期望目标路径是文件,而得到目录.");
			return;
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		// 创建 转换工厂
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer tf = factory.newTransformer();

		// 创建DOMSource对象
		DOMSource source = new DOMSource(doc);
		// 创建BufferedWriter,做为将来输出的包装流
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		// 创建 StreamResult对象
		StreamResult result = new StreamResult(bw);

		// 设置输出格式
		tf.setOutputProperty("encoding", "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		// 转换
		tf.transform(source, result);

		// 关闭资源
		bw.close();
	}

	/**************************************
	 * 把指定的Document文档树转换成 字符串.
	 * 
	 * @param doc
	 * @return
	 */
	public static String toString(Document doc) throws Exception {
		// 创建 转换工厂
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer tf = factory.newTransformer();

		// 创建DOMSource对象
		DOMSource source = new DOMSource(doc);
		// 创建 StringWriter 对象
		StringWriter sw = new StringWriter();
		// 创建 StreamResult对象
		StreamResult result = new StreamResult(sw);

		// 设置输出格式
		tf.setOutputProperty("encoding", "utf-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		// 转换
		tf.transform(source, result);

		return sw.getBuffer().toString();
	}

	/**********************************
	 * 本方法试着组合多个Document到一个document中
	 * 
	 * @param root
	 * @param targets
	 */
	public static void combinDocument(Document root, List<Document> targets) {
		for (Document d : targets) {
			root.getFirstChild().appendChild(
					root.adoptNode(d.getDocumentElement()));
		}
	}
}
