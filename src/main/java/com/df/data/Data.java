/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yejf
 * @date 2014-3-4 下午3:37:26
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5232057422733744125L;

	private Integer id;	//主键
	
	private long version;	//版本号
	
	private Date modify; //修改时间
	
	private Date create; //创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getModify() {
		return modify;
	}

	public void setModify(Date modify) {
		this.modify = modify;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}
	
}
