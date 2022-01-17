package com.danieltns.usecase2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danieltns.usecase2.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword, String sameKeyword);
}
