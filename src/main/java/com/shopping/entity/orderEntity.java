package com.shopping.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="order_shopping")
public class orderEntity {

	@Id
	@Column(name="orderID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderID;
	
	@Column(name="userID")
	private int userID;
	
	@Column(name="date")
	private String date;
	
	@Column(name="coupon")
	private String coupon;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="qty")
	private int qty;
	
	public orderEntity() {}

	public orderEntity(int orderID, int userID, String date, String coupon, int amount,int qty) {
		this.orderID = orderID;
		this.userID = userID;
		this.date = date;
		this.coupon = coupon;
		this.amount = amount;
		this.qty = qty;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
