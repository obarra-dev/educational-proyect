package com.omm.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.omm.item.model.Item;
import com.omm.item.model.Product;

@Service("itemServiceRestTemplate")
public class ItemServiceRestTemplate implements ItemService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Item> findAll(){
		Product[] productsArray = restTemplate.getForObject("http://product-service/list", Product[].class);
		List<Product> products = Arrays.asList(productsArray);
		List<Item> items = products.stream().map(p -> new Item(p, 1L))
				.collect(Collectors.toList());
		return items;
	}
	
	public Item findById(Long id, Long amount) {
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		Product product = restTemplate.getForObject("http://product-service/product/{id}", Product.class, params);
		return new Item(product, amount);
	}

	@Override
	public Product save(Product product) {
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		ResponseEntity<Product> response = restTemplate.exchange("http://product-service/create", HttpMethod.POST, body, Product.class);
		return response.getBody();
	}

	@Override
	public Product update(Product product, Long id) {
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		ResponseEntity<Product> reseponse = restTemplate.exchange("http://product-service/edit/{id}", HttpMethod.PUT, body, Product.class, params);
		return reseponse.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", id.toString());
		restTemplate.delete("http://product-service/delete/{id}", uriVariables);
	}

}
