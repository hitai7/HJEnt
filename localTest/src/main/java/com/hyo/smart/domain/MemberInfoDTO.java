package com.hyo.smart.domain;

import java.util.Date;

public class MemberInfoDTO {
	
	private String userId;
	private String password;
	private String newPassword;
	private String name;
	private String roll;
	private String note;
	private Date loginDate;
	private String paramType;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	
	
	@Override
	public String toString() {
		return "MemberInfoDTO [userId=" + userId + ", password=" + password + ", newPassword=" + newPassword + ", name="
				+ name + ", roll=" + roll + ", note=" + note + ", loginDate=" + loginDate
				+ ", paramType=" + paramType + "]";
	}
}
