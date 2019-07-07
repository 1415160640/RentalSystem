package cn.xiaowo.rental.domain;
//订单模型
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private String itemid; //订单编号
	private Date date;
	private int quantity; //数量
	private double total; //总计
	private int state; //状态 0表示预定 1表示订房  2表示取消预定  3等待退房  4成功订单 5失败订单 
	private String pid; //房源信息编号
	private String uid; //求助人编号
	private String oid; //出租人编号
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Order(String itemid, Date date, int quantity, double total, int state, String pid, String uid, String oid) {
		super();
		this.itemid = itemid;
		this.date = date;
		this.quantity = quantity;
		this.total = total;
		this.state = state;
		this.pid = pid;
		this.uid = uid;
		this.oid = oid;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [itemid=" + itemid + ", date=" + date + ", quantity=" + quantity + ", total=" + total + ", state="
				+ state + ", pid=" + pid + ", uid=" + uid + ", oid=" + oid + "]";
	}


}
