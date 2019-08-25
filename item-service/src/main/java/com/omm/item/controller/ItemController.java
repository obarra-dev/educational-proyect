package com.omm.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.omm.item.model.Item;
import com.omm.item.model.Product;
import com.omm.item.service.ItemService;

@RefreshScope
@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemServiceFeign")
	private ItemService itemService;
	
	@Autowired
	private Environment environment;
	
	@Value("${configuration.message}")
	private String message;
	
	@GetMapping("/list")
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
//	@HystrixCommand(fallbackMethod = "defaultProductAmount")
	@GetMapping("product/{id}/amount/{amount}")
	public Item find(@PathVariable Long id, @PathVariable Long amount) {
		return itemService.findById(id, amount);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		return itemService.update(product, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}
	public Item defaultProductAmount(Long id, Long amount) {
		Product product = new Product();
		product.setPrice(566.8);
		Item item = new Item(product, amount);
		return item;
	}
	
	@GetMapping("get-config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
		Map<String, String> map = new HashMap<>();
		map.put("message",message);
		map.put("port", port);
		if(environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")) {
			map.put("developmessage", environment.getProperty("develop.message"));
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
