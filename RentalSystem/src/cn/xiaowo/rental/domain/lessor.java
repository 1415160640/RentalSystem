package cn.xiaowo.rental.domain;

import java.util.Date;
//出租人模型
public class lessor {
	private String uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String telephone;
	private Date birthday;
	private String sex;
	private String address;
	private String id;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private int state; //#状态：0=未激活，1=已激活
	private String code; //#激活码
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public lessor(String uid, String username, String password, String name, String email, String telephone,
			Date birthday, String sex, String address, String id, int state, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.address = address;
		this.id = id;
		this.state = state;
		this.code = code;
	}
	public lessor() {
		
	}
	@Override
	public String toString() {
		return "lessor [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex
				+ ", address=" + address + ", id=" + id + ", state=" + state + ", code=" + code + "]";
	}
	
	

}
