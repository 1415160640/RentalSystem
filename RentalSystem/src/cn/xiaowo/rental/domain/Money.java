package cn.xiaowo.rental.domain;
//财务模型
public class Money {
     private String id;//财务表id
     private String userid;//用户编号
     private double money;//用户余额
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Money(String id, String userid, double money) {
		super();
		this.id = id;
		this.userid = userid;
		this.money = money;
	}
	public Money() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Money [id=" + id + ", userid=" + userid + ", money=" + money + "]";
	}   
}
