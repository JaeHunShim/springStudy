package com.overware.domain;

public class ProductVO {
	
	private String name;
	private double price;
	
	public ProductVO(String name,double price) {
		super();
		this.name=name;
		this.price=price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}


	@Override
	public String toString() { //각각의 객체가 가지고 있는 값을 문자열로 만들어서 리턴하기 위해서 사용
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
	
}
