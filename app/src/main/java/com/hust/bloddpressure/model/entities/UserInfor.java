/**
 * Copyright(C) 2020 Hust
 * UserInfor.java Oct 25, 2020 Trần Bá Đạt
 */
package com.hust.bloddpressure.model.entities;

import java.io.Serializable;

/**
 * @author Trần Bá Đạt
 *
 */
public class UserInfor implements Serializable {
	/**
	 * Class chứa thuộc tính cần lấy ra trong các bảng
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int roomId;
	private int age;
	private int rule;
	private String fullName;
	private String tel;
	private String room;
	private String diseaseName;
	private String salt;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public String getFullname() {
		return fullName;
	}

	public void setFullname(String fullname) {
		this.fullName = fullname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDiseasename() {
		return diseaseName;
	}

	public void setDiseasename(String diseasename) {
		this.diseaseName = diseasename;
	}

}
