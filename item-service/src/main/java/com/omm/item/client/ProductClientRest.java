package com.omm.item.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.omm.item.model.Product;

@FeignClient(name = "product-service")
public interface ProductClientRest {
	
	@GetMapping("/list")
	List<Product> list();
	
	@GetMapping("/product/{id}")
	Product getProduct(@PathVariable Long id);

}
