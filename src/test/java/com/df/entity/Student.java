package com.df.entity;

import java.util.Arrays;
import java.util.Date;

import com.df.generate.Gender;
import com.df.util.DateUtil;

public class Student {

	private int id;
	
	private String name;
	
	private String[] hobby;
	
	private int age;
	
	private Date birth;

	private Gender gender;
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

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

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", hobby=");
		builder.append(Arrays.toString(hobby));
		builder.append(", age=");
		builder.append(age);
		builder.append(", birth=");
		builder.append(DateUtil.toString(birth));
		builder.append(", gender=");
		builder.append(gender);
		builder.append("]");
		return builder.toString();
	}
	
}
