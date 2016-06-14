package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Item_event;
import com.example.domain.Item_goods;
import com.example.repository.Item_eventRepository;
import com.example.repository.Item_goodsRepository;

@RestController
@RequestMapping("/")
public class Controller {
	
	@Autowired
	Item_eventRepository item_eventRepository;
	
	@Autowired
	Item_goodsRepository item_goodsRepository;
	
	@RequestMapping("/")
	public ArrayList<String> command_list() {

		ArrayList<String> commandList = new ArrayList<String>();
		commandList.add("COMMAND : /{name}");
		commandList.add("event");
		commandList.add("goods");
		
		return commandList;
	}
	
	
	@RequestMapping("/event")
	public List<Item_event> item_eventDetail() {
		List<Item_event> item_event = item_eventRepository.findAll();
		return item_event;
	}
	
	@RequestMapping("/goods")
	public List<Item_goods> item_goodsDetail() {
		List<Item_goods> item_goods = item_goodsRepository.findAll();
		return item_goods;
	}
	
}
