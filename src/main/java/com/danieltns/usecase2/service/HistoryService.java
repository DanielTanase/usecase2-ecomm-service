package com.danieltns.usecase2.service;

import java.util.List;

import com.danieltns.usecase2.entity.History;

public interface HistoryService extends CrudService<History, Long> {

	List<History> findByUserId(long userId);
	
}
