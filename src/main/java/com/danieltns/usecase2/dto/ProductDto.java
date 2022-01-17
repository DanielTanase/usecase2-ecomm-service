package com.danieltns.usecase2.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProductDto {
	
	@NotEmpty(message="Product name cannot be null/empty!")
	@Size(min=2, max=30, message="Product name must be between 2 and 30 characters!")
	private String name;
	
	@NotEmpty(message="Product description cannot be null/empty!")
	@Size(min=20, max=255, message="Product description must be between 20 and 255 characters!")
	private String description;
	
	@Min(value=1, message="Product price should greater than 0!")
	private int price;
	
	@Min(value=1, message="Pieces in stock should be greater than 0!")
	private int piecesInStock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPiecesInStock() {
		return piecesInStock;
	}

	public void setPiecesInStock(int piecesInStock) {
		this.piecesInStock = piecesInStock;
	}
}
