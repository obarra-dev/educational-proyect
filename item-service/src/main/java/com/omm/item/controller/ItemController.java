package com.omm.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.omm.item.model.Item;
import com.omm.item.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
	@GetMapping("product/{id}/amount/{amount}")
	public Item find(@PathVariable Long id, @PathVariable Long amount) {
		return itemService.findById(id, amount);
	}
}
