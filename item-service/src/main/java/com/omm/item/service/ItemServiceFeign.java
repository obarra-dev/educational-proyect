package com.omm.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceRunnable;
import org.springframework.stereotype.Service;

import com.omm.common.model.entity.Product;
import com.omm.item.client.ProductClientRest;
import com.omm.item.model.Item;

import brave.Tracer;

@Service("itemServiceFeign")
public class ItemServiceFeign implements ItemService{

	@Autowired
	private ProductClientRest productClientRest;
	
	@Autowired
	private Tracer tracer;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = productClientRest.list();
		List<Item> items = products.stream().map(p -> new Item(p, 1L))
				.collect(Collectors.toList());
		return items;
	}

	@Override
	public Item findById(Long id, Long amount) {
		try {
			Product product = productClientRest.getProduct(id);
			return new Item(product, amount);
		} catch (Exception e) {
			// only for test purpose
			tracer.currentSpan().tag("test-tag", "I can not connet with product service");
			throw new RuntimeException(e);
		}
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
