package cn.xiaowo.rental.domain;

import java.util.Date;
//房源对象
public class Product {
	private String pid; //编号
	private String pname; //名称
	private double area;//面积
	private double price;//价格
	private String pimage;//图片路径
	private int is_hot;//是否热门
	private String pdesc;//描述
	private int pflag;//状态 0 不可用  1可用  2已出租 3已预定 4线下已出租
	private String cid;//所在分类id
	private String uid;//出租人id
	private String aid;//地区分类id
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPflag() {
		return pflag;
	}
	public void setPflag(int pflag) {
		this.pflag = pflag;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public Product(String pid, String pname, double area, double price, String pimage, int is_hot,
			String pdesc, int pflag, String cid, String uid, String aid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.area = area;
		this.price = price;
		this.pimage = pimage;
		this.is_hot = is_hot;
		this.pdesc = pdesc;
		this.pflag = pflag;
		this.cid = cid;
		this.uid = uid;
		this.aid = aid;
	}
	public Product() {
		
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", area=" + area + ", price=" + price + ", pimage=" + pimage
				+ ", is_hot=" + is_hot + ", pdesc=" + pdesc + ", pflag=" + pflag + ", cid=" + cid
				+ ", uid=" + uid + ", aid=" + aid + "]";
	}
	
	
	
}
