package cn.xiaowo.rental.domain;

import java.util.Date;
//管理员
public class Admin {
	private String uid;
	private String username;
	private String password;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public Admin(String uid, String username, String password) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		
	}
	public Admin() {
		
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + " ]";
	}

}
