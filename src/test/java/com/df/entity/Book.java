/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.entity;

import java.util.Date;

import com.df.util.DateUtil;

/**
 * @author yejf
 * @date 2014-3-11 下午9:56:54
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class Book {

	private int id;
	private String name;
	private String author;
	private double price;
	private Date publish_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", author=");
		builder.append(author);
		builder.append(", price=");
		builder.append(price);
		builder.append(", publish_date=");
		builder.append(DateUtil.toString(publish_date));
		builder.append("]");
		return builder.toString();
	}

	
	
}
