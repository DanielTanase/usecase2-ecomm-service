package com.danieltns.usecase2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieltns.usecase2.entity.History;
import com.danieltns.usecase2.repository.HistoryRepository;
import com.danieltns.usecase2.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public Iterable<History> findAll() {
		return historyRepository.findAll();
	}

	@Override
	public History findById(Long id) {
		return historyRepository.findById(id).orElse(null);
	}

	@Override
	public History saveOrUpdate(History object) {
		return historyRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		historyRepository.deleteById(id);
	}

	@Override
	public List<History> findByUserId(long userId) {
		return historyRepository.findByUserId(userId);
	}

}
