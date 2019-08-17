package com.omm.item.service;

import java.util.List;
import com.omm.item.model.Item;

public interface ItemService {
	
	 List<Item> findAll();
	 
	 Item findById(Long id, Long amount);
	 
}
