package com.danieltns.usecase2.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProductBuyingDto {
	
	@Min(value=0, message="User id must be positive!")
	private long userId;
	
	@NotEmpty (message="Account number cannot be null/empty!")
	@Size(min=6, max=6, message="Account number must have 6 digits!")
	@Pattern(regexp="^\\d{6}$", message="Please specify sender account!")
	private String fromAccountNumber;
	
	@Min(value=0, message="Product id must be positive!")
	private long productId;
	
	@Min(value=1, message="The number of products must be bigger than or equal to 1!")
	private int numberOfPieces;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}
	
}
