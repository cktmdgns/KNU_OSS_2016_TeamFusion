package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.domain.Item_event;

public interface Item_eventRepository extends CrudRepository<Item_event, String> {
	
	List<Item_event> findAll();
	
}