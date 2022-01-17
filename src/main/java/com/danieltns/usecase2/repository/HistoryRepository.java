package com.danieltns.usecase2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danieltns.usecase2.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
	List<History> findByUserId(long userId);
}
