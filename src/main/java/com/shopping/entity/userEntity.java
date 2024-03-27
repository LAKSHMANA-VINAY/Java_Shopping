package com.shopping.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_shopping")
public class userEntity {

	@Id
	@Column(name="userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	
	@Column(name="userName")
	private String userName;
	
	public userEntity() {}

	public userEntity(int userID, String userName) {
		this.userID = userID;
		this.userName = userName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
