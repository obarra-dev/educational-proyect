package com.omm.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.omm.item.model.Item;
import com.omm.item.model.Product;
import com.omm.item.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemServiceFeign")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
//	@HystrixCommand(fallbackMethod = "defaultProductAmount")
	@GetMapping("product/{id}/amount/{amount}")
	public Item find(@PathVariable Long id, @PathVariable Long amount) {
		return itemService.findById(id, amount);
	}
	
	public Item defaultProductAmount(Long id, Long amount) {
		Product product = new Product();
		product.setPrice(566.8);
		Item item = new Item(product, amount);
		return item;
	}
}
