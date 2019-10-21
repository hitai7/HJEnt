package com.hyo.smart.domain;

import java.util.List;

public class UserInfoDTO {
	
	private String userId;
	private String name;
	private List<String> roll;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getRoll() {
		return roll;
	}
	public void setRoll(List<String> roll) {
		this.roll = roll;
	}
	
	@Override
	public String toString() {
		return "UserInfoDTO [userId=" + userId + ", name=" + name + ", roll=" + roll + "]";
	}
}
