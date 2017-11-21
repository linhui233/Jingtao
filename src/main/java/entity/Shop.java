package entity;

import java.io.Serializable;

public class Shop implements Serializable {
	private String name;
	private Double money;
	private String color;
	private Integer sum;
	private Double nice;
	private Integer imgId;
	private Integer buySum;
	private Double Sumprice;
	public Double getSumprice() {
		return Sumprice;
	}
	public void setSumprice(Double sumprice) {
		Sumprice = sumprice;
	}
	public Integer getBuySum() {
		return buySum;
	}
	public void setBuySum(Integer shuliang) {
		this.buySum = shuliang;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Double getNice() {
		return nice;
	}
	public void setNice(Double nice) {
		this.nice = nice;
	}
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	@Override
	public String toString() {
		return "Shop [name=" + name + ", money=" + money + ", color=" + color + ", sum=" + sum + ", nice=" + nice
				+ ", imgId=" + imgId + ", buySum=" + buySum + ", Sumprice=" + Sumprice + "]";
	}
	
	
	
}
