package cn.xiaowo.rental.domain;
//地区模型
public class Area {
	private String aid;
    private double house_price;
    private double manger_price;
    private String aname;
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public double getHouse_price() {
		return house_price;
	}
	public void setHouse_price(double house_price) {
		this.house_price = house_price;
	}
	public double getManger_price() {
		return manger_price;
	}
	public void setManger_price(double manger_price) {
		this.manger_price = manger_price;
	}
	public Area(String aid, double house_price, double manger_price, String aname) {
		super();
		this.aid = aid;
		this.house_price = house_price;
		this.manger_price = manger_price;
		this.aname = aname;
	}
    public Area() {
  
    }
	@Override
	public String toString() {
		return "Area [aid=" + aid + ", house_price=" + house_price + ", manger_price=" + manger_price + ", aname="
				+ aname + "]";
	}
    

}
