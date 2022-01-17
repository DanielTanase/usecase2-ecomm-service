package com.danieltns.usecase2.service;

public interface CrudService<T, I> {
	
	Iterable<T> findAll();
	
	T findById(I id);
	
	T saveOrUpdate(T object);
	
	void deleteById(I id);
}
