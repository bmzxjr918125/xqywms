package com.entity.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>ClassName: Province</p>
 * <p>@Description: 省</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-23上午9:59:37</p>
 */
@Entity
@Table(name = "province")
public class Province implements Serializable {
    private static final long serialVersionUID = -4254057414023642209L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String province;
	
	private int flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
