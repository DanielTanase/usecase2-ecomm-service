package com.danieltns.usecase2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danieltns.usecase2.dto.ProductBuyingDto;
import com.danieltns.usecase2.dto.ProductDto;
import com.danieltns.usecase2.entity.History;
import com.danieltns.usecase2.entity.Product;
import com.danieltns.usecase2.service.ProductService;
import com.danieltns.usecase2.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.PRODUCT_CONTROLLER)
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(AppConstants.GET_ALL_PRODUCTS)
	public List<Product> getAllProducts() {
		return (List<Product>) productService.findAll();
	}
	
	@GetMapping(AppConstants.SEARCH_PRODUCT)
	public List<Product> searchProduct(@RequestParam String keyword) {
		return productService.searchProduct(keyword);
	}
	
	@PostMapping(AppConstants.REGISTER_PRODUCT)
	public Product addProduct(@Valid @RequestBody ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return productService.saveOrUpdate(product);
	}
	
	@PostMapping(AppConstants.BUY_PRODUCT)
	public History buyProduct(@Valid @RequestBody ProductBuyingDto productBuyingDto) {
		return productService.buyProduct(productBuyingDto);
	}
}
