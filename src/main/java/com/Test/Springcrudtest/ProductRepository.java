package com.Test.Springcrudtest;

import org.springframework.data.repository.CrudRepository;

//crud Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	public Product findByName(String name);

}
