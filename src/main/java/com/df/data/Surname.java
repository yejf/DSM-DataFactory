package com.df.data;

/*********************
 * 中国百家姓氏
 * @author yejf
 *
 */
public class Surname extends Data {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779599056129996646L;

	private int seq;	//排名
	
	private String name; //姓氏

	public Surname() {
		super();
	}

	public Surname(int seq, String name) {
		super();
		this.seq = seq;
		this.name = name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Surname [seq=");
		builder.append(seq);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + seq;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Surname other = (Surname) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (seq != other.seq)
			return false;
		return true;
	}
	
}
