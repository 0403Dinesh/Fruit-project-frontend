package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fruits {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String color;
	private String price;
	private String vitamins;
	private String season;
	private String avalability;
	
	@Column(length=1000000)
	private byte[] image;
	
	
	public Fruits() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Fruits(int id, String name, String color, String price, String vitamins, String season, String avalability,
			byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.price = price;
		this.vitamins = vitamins;
		this.season = season;
		this.avalability = avalability;
		this.image = image;
	}


	public Fruits(String name, String color, String price, String vitamins, String season, String avalability,
			byte[] image) {
		super();
		this.name = name;
		this.color = color;
		this.price = price;
		this.vitamins = vitamins;
		this.season = season;
		this.avalability = avalability;
		this.image = image;
	}
	
	public Fruits( String name, String color, String price, String vitamins, String season, String avalability
			) {
		this.name = name;
		this.color = color;
		this.price = price;
		this.vitamins = vitamins;
		this.season = season;
		this.avalability = avalability;
		
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getcolor() {
		return color;
	}
	public void setcolor(String color) {
		this.color = color;
	}
	public String getprice() {
		return price;
	}
	public void setprice(String price) {
		this.price = price;
	}
	public String getvitamins() {
		return vitamins;
	}
	public void setvitamins(String vitamins) {
		this.vitamins = vitamins;
	}
	public String getseason() {
		return season;
	}
	public void setPrice(String season) {
		this.season = season;
	}
	public String getavalability() {
		return avalability;
	}
	public void setavalability(String avalability) {
		this.avalability = avalability;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Fruits [id=" + id + ", name=" + name + ", color=" + color + ", price=" + price + ", vitamins="
				+ vitamins + ", season=" + season + ", avalability=" + avalability + "]";
	}
	
	
	
	
	
}
