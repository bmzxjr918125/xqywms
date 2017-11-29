package com.entity.trade;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.entity.admin.Admin;
import com.entity.user.User;
/**
 * <p>ClassName: Trade</p>
 * <p>@Description: 用户交易信息表</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2017-8-15上午10:47:51</p>
 */
@Entity
@Table(name = "trade")
public class Trade implements Serializable {
	private static final long serialVersionUID = 6750873172848528230L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 交易号
	 */
	@Column(unique=true,nullable=false,length=30)
	private String tradeNo;
	/**
	 * 交易金额
	 */
	@Column(nullable=false,columnDefinition="double(8,1) default  '0.0'")
	private double amount;
	/**
	 * 实际支付金额
	 */
	@Column(nullable=false,columnDefinition="double(8,1) default  '0.0'")
	private double payAmount;
	/**
	 * 使用积分
	 */
	@Column(nullable=false,columnDefinition="double(8,1) default  '0.0'")
	private double useIntegral;
	/**
	 * 收入积分
	 */
	@Column(nullable=false,columnDefinition="double(8,1) default  '0.0'")
	private double incomeIntegral;
	/**
	 * 交易类型 1使用积分
	 */
	private int type;
	/**
	 * 对应店员
	 */
	@ManyToOne
	@JoinColumn(name="adminId",nullable=false)
	private Admin admin;
	/**
	 * 对应用户  该项可以为空 （未填写手机号时）
	 */
	@ManyToOne
	@JoinColumn(name="userId",nullable=true)
	private User user;
	
	private Date createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public double getUseIntegral() {
		return useIntegral;
	}
	public void setUseIntegral(double useIntegral) {
		this.useIntegral = useIntegral;
	}
	public double getIncomeIntegral() {
		return incomeIntegral;
	}
	public void setIncomeIntegral(double incomeIntegral) {
		this.incomeIntegral = incomeIntegral;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 生成TradeNo
	 * @param linlincount 邻邻账号
	 * @param mili 当前时间毫秒数
	 * @return
	 */
	public static String createTradeNo(String phoneNumber){
		
		//log.info(new Date() + "开始生成订单号");
		String no="";
		if(phoneNumber != null && phoneNumber.length() > 6){
			Random random = new Random();
			no=random.nextInt(Integer.parseInt(phoneNumber.substring(phoneNumber.length()-7,phoneNumber.length()-1)))+"";
		}
		
		String orderNumber = "100"+no+System.currentTimeMillis();
		//log.info(new Date() + "生成订单号：" + orderNumber);
		return orderNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
