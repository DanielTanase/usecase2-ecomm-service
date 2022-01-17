package com.danieltns.usecase2.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danieltns.usecase2.dto.ProductBuyingDto;
import com.danieltns.usecase2.dto.TransactionDto;
import com.danieltns.usecase2.dto.TransferRequestDto;
import com.danieltns.usecase2.entity.History;
import com.danieltns.usecase2.entity.Product;
import com.danieltns.usecase2.feignclient.BankClient;
import com.danieltns.usecase2.repository.ProductRepository;
import com.danieltns.usecase2.service.HistoryService;
import com.danieltns.usecase2.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private BankClient bankClient;
	
	@Value("${store.account.number}")
	private String storeAccountNumber;
	
	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product saveOrUpdate(Product object) {
		return productRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	@Override
	public List<Product> searchProduct(String keyword) {
		return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
	}

	@Transactional
	@Override
	public History buyProduct(ProductBuyingDto productBuyingDto) {
		if (storeAccountNumber == null || storeAccountNumber.length() != 6) {
			throw new IllegalStateException("Please set an appropriate account number in properties file! (store.account.number)");
		}
		
		Product product = findById(productBuyingDto.getProductId());
		if (product == null) {
			throw new IllegalArgumentException("The product id is invalid! No product with this id!");
		} else if (product.getPiecesInStock() < productBuyingDto.getNumberOfPieces()) {
			throw new IllegalArgumentException("Not enought available pieces! (actual stock: " + product.getPiecesInStock() + ")");
		}
		
		History history = new History();
		historyService.saveOrUpdate(history);
		
		TransferRequestDto transferRequestDto = new TransferRequestDto();
		transferRequestDto.setValue(product.getPrice() * productBuyingDto.getNumberOfPieces());
		transferRequestDto.setFromAccountNumber(productBuyingDto.getFromAccountNumber());
		transferRequestDto.setToAccountNumber(storeAccountNumber);
		transferRequestDto.setDescription("Payment for order number " + history.getId() + ".");
		
		TransactionDto transactionDto = bankClient.transferFund(transferRequestDto);
		
		history.setPieces(productBuyingDto.getNumberOfPieces());
		history.setProduct(product);
		history.setDateTime(LocalDateTime.now());
		history.setTotalPrice(transactionDto.getValue());
		history.setUserId(productBuyingDto.getUserId());
		history.setFromAccount(transactionDto.getFromAccount().getAccountNumber());
		history.setTransactionId(transactionDto.getId());
		
		product.setPiecesInStock(product.getPiecesInStock() - productBuyingDto.getNumberOfPieces());
		
		return history;
	}
	
}
