package com.omm.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.omm.item.model.Item;
import com.omm.item.model.Product;

@Service
public class ItemService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Item> findAll(){
		Product[] productsArray = restTemplate.getForObject("http://localhost:8080/list", Product[].class);
		List<Product> products = Arrays.asList(productsArray);
		List<Item> items = products.stream().map(p -> new Item(p, 1L))
				.collect(Collectors.toList());
		return items;
	}
	
	public Item findById(Long id, Long amount) {
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		Product product = restTemplate.getForObject("http://localhost:8080/product/{id}", Product.class, params);
		
		return new Item(product, amount);
	}

}
