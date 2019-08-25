package com.omm.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omm.common.model.entity.Product;
import com.omm.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Environment environment;
	
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Product findById(Long id){
		Product product = productRepository.findById(id).orElse(null);
		product.setPort(environment.getProperty("local.server.port"));
		return product;
	}
	
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public void deleteById(Long id) {
		productRepository.deleteById(id);;
	}
	

}
