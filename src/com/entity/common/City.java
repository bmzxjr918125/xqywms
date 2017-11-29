package com.entity.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * <p>ClassName: City</p>
 * <p>@Description: 市</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-23上午9:59:54</p>
 */
@Entity
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = -3218629897871769776L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String city;
	@ManyToOne
    @JoinColumn(name = "provinceId")
	private Province province;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
