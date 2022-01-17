package com.danieltns.usecase2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danieltns.usecase2.entity.History;
import com.danieltns.usecase2.service.HistoryService;
import com.danieltns.usecase2.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.HISTORY_CONTROLLER)
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	
	@GetMapping(AppConstants.GET_ALL_HISTORY)
	public List<History> getAllHistory() {
		return (List<History>) historyService.findAll();
	}
	
	@GetMapping
	public List<History> getByUserId(@RequestParam long userId) {
		return historyService.findByUserId(userId);
	}
}
