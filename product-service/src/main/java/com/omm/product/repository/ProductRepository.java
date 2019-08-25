package com.omm.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.omm.common.model.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
