package com.danieltns.usecase2.service;

import java.util.List;

import com.danieltns.usecase2.dto.ProductBuyingDto;
import com.danieltns.usecase2.entity.History;
import com.danieltns.usecase2.entity.Product;

public interface ProductService extends CrudService<Product, Long> {

	History buyProduct(ProductBuyingDto productBuyingDto);

	List<Product> searchProduct(String keyword);
	
}
