package org.example;

import java.sql.Blob;

public class Products {
	private int id;
	private String description;
	private String price;
	private  String category;
	private Blob image;
	private String status;

	public Products(int id, String description, String price, String category, Blob image, String status) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public Blob getImage() {
		return image;
	}

	public String getStatus() {
		return status;
	}
}
