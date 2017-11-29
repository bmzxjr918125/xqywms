package com.entity.vo;

import java.io.Serializable;
/**
 * <p>ClassName: ReciveAddressInfo</p>
 * <p>@Description: 收货地址vo</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-15下午1:38:57</p>
 */
public class ReciveAddressVo implements Serializable {
    private static final long serialVersionUID = -1381425546777505738L;
    /**
	 * 收货人姓名
	 */
	private String name;
	/**
	 * 收货人联系方式
	 */
	private String phoneNumber;
	/**
	 * 地址信息
	 */
	private AddressVo addressVo;
	/**
	 * 邮编
	 */
	private String zipcode; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

    public AddressVo getAddressVo() {
        return addressVo;
    }

    public void setAddressVo(AddressVo addressVo) {
        this.addressVo = addressVo;
    }

	

	
	
}
