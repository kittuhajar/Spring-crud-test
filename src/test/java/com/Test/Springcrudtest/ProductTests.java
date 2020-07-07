package com.Test.Springcrudtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductTests {
	
	@Autowired
	private ProductRepository repo;
	
	@Test
	@Rollback(false)
	public void testCreateProduct()
	{
		Product product = new Product("iphone 10" , 789);
		Product savedProduct = repo.save(product);
	
		assertNotNull(savedProduct);
	}
	@Test
	public void testFindByProductName()
	{
		String name = "iphone 10";
		Product product =repo.findByName(name);
		assertThat(product.getName()).isEqualTo(name);
	}
	
	@Test
	@Rollback(false)
	public void testUpdateProduct()
	{
		String productNmae = "kindle Reader";
		Product product = new Product(productNmae,199);
		product.setId(2);
		
		repo.save(product);
		
		Product updatedproduct = repo.findByName(productNmae);
		
		assertThat(updatedproduct.getName()).isEqualTo(productNmae);
	}
	
	@Test
	public void testListProducts() {
		List<Product> products = (List<Product>) repo.findAll();
		
		for(Product product : products)
		{
			System.out.println(product);
		}
		assertThat(products).size().isGreaterThan(0);
	}

	@Test
	@Rollback(false)
	public void testdeleteproduct()
	{
		Integer id =2;
		
		boolean isExistbeforedelete = repo.findById(id).isPresent();
		repo.deleteById(id);
		boolean notExistAfterDelete = repo.findById(id).isPresent();
		
		assertTrue(isExistbeforedelete);
		assertFalse(notExistAfterDelete);
	}
}
