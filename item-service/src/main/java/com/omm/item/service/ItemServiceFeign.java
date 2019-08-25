package com.omm.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omm.item.client.ProductClientRest;
import com.omm.item.model.Item;
import com.omm.item.model.Product;

@Service("itemServiceFeign")
public class ItemServiceFeign implements ItemService{

	@Autowired
	private ProductClientRest productClientRest;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = productClientRest.list();
		List<Item> items = products.stream().map(p -> new Item(p, 1L))
				.collect(Collectors.toList());
		return items;
	}

	@Override
	public Item findById(Long id, Long amount) {
		Product product = productClientRest.getProduct(id);
		return new Item(product, amount);
	}

	@Override
	public Product save(Product product) {
		return productClientRest.create(product);
	}

	@Override
	public Product update(Product product, Long id) {
		return productClientRest.edit(product, id);
	}

	@Override
	public void delete(Long id) {
		productClientRest.delete(id);
	}

}
