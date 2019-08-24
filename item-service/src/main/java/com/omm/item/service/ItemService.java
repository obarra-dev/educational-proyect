package com.omm.item.service;

import java.util.List;
import com.omm.item.model.Item;
import com.omm.item.model.Product;

public interface ItemService {
	
	 List<Item> findAll();
	 
	 Item findById(Long id, Long amount);
	 
	 Product save(Product product);
	 
	 Product update(Product product, Long id);
	 
	 void delete(Long id);
	 
}
