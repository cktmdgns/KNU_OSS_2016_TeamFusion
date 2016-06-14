package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.domain.Item_goods;


public interface Item_goodsRepository extends CrudRepository<Item_goods, String> {
	
	List<Item_goods> findAll();
	
}
