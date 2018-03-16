package com.df.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区实体类
 * @author Administrator
 *
 */
public class Area extends Data {

	private static final long serialVersionUID = 3091806785347201985L;
	
	private String name;//区域名(a_name)
	private String code;//区域码(a_code)
	private int level;//区域等级(a_level)
	
	private Area parent;
	private List<Area> children;
	
	public Area() {
	}
	
	public Area(Integer id , String name) {
		this.setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		this.children = children;
	}
	
	/**********************
	 * 互相注册关系
	 * @param childenArea  子区域
	 */
	public void addChilden(Area childenArea){
		if(children == null){
			this.children = new ArrayList<Area>();
		}
		children.add(childenArea);
		childenArea.setParent(this);
	}
	
	@Override
	public String toString() {
		return "Area [name=" + name + ", code=" + code + ", level=" + level
				+ "]";
	}
	
}