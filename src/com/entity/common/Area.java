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
 * <p>ClassName: Area</p>
 * <p>@Description: 区</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-23上午10:00:08</p>
 */
@Entity
@Table(name = "area")
public class Area implements Serializable {
    private static final long serialVersionUID = -338907568892904866L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String area;
	
	@ManyToOne
	@JoinColumn(name = "cityId")
	private City city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

	
}
