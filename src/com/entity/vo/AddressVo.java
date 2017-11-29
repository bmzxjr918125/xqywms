package com.entity.vo;

import java.io.Serializable;
/**
 * <p>ClassName: AddressVo</p>
 * <p>@Description: 地址vo</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-15下午1:38:57</p>
 */
public class AddressVo implements Serializable {
    private static final long serialVersionUID = -8743504916283636376L;
    
	/**
	 * 所在省
	 */
	private String province;
	/**
	 * 所在市
	 */
	private String city;
	/**
	 * 所在区
	 */
	private String area;
	
	/**
	 * 详细地址
	 */
	private String address;
	
	
	
    public AddressVo() {
        super();
    }
    public AddressVo(String province, String city, String area, String address) {
        super();
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
  
}
