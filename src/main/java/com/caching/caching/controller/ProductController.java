//package com.caching.caching.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.caching.caching.entity.Product;
//import com.caching.caching.service.ProductService;
//
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//
//
//@RestController
//public class ProductController {
//
//	@Autowired
//	private ProductService productService;
//	
//	@PostMapping("/product")
//	public void saveProduct(@RequestBody Product product) {
//		 productService.saveProduct(product);
//	}
//	
//	@GetMapping("/product")
//	@Cacheable(value = "product")
//	public List<Product>findAllProduct(){
//		return productService.find();	
//		}
//	@GetMapping("/product/{id}")
//	@Cacheable(value = "product", key = "#id")
//	public Product findById(@PathVariable int id) {
//		return productService.findById(id);
//	}
//	
//	@PutMapping("/product/{id}")
//	@CachePut(value = "product", key = "#id")
//	public Product editProduct(@PathVariable int id, @RequestBody Product product) {
//		return productService.updateProduct(id,product);
//	}
//	
//	@DeleteMapping("/product/{id}")
//	@CacheEvict(cacheNames = "product", key = "#id", beforeInvocation = true)
//	public String removeProductById(@PathVariable int id) {
//		productService.remove(id);
//		return "Product deleted";
//	}
//	
//}
