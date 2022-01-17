package com.danieltns.usecase2.utils;

public class AppConstants {
	public static final String PRODUCT_CONTROLLER = "/product";
	public static final String HISTORY_CONTROLLER = "/history";
	public static final String GET_ALL_PRODUCTS = "/all";
	public static final String SEARCH_PRODUCT = "/search";
	public static final String REGISTER_PRODUCT = "/register";
	public static final String BUY_PRODUCT = "/buy";
	public static final String GET_ALL_HISTORY = "/all";
	
	/*
	 * Feign Client
	 */
	public static final String BANK_SERVICE_URL = "http://BANK-SERVICE/bank";
	public static final String TRANSACTION_CONTROLLER = "/transfer";
	public static final String TRANSFER_FUND = "/fundTransfer";
	
	private AppConstants() {
	    throw new IllegalStateException("Constants class");
	  }
}
