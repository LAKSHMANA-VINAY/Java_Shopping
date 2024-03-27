package com.shopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trans_shopping")
public class transEntity {

	@Id
	@Column(name="transID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transID;
	
	@Column(name="orderID")
	private int orderID;
	
	@Column(name="date")
	private String date;
	
	@Column(name="coupon")
	private String coupon;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="status")
	private String status;
	
	public transEntity() {}

	public transEntity(int transID, int orderID, String date, String coupon, int amount, String status) {
		this.transID = transID;
		this.orderID = orderID;
		this.date = date;
		this.coupon = coupon;
		this.amount = amount;
		this.status = status;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
