//package com.caching.caching.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.caching.caching.entity.Product;
//import com.caching.caching.repository.ProductRepository;
//
//@Service
//public class ProductService {
//	
//	@Autowired
//	private ProductRepository productRepository; 
//
//	public void saveProduct(Product product) {
//		
//		productRepository.save(product);
//	}
//
//	public Product findById(int id) {
//		System.out.println("service method is called");
//		return productRepository.findById(id).get();
//	}
//
//	public Product updateProduct(int id,Product product) {
//		
//		Product productExist=productRepository.findById(id).get();
//		if(productExist!=null) {
//			productExist.setName(product.getName());
//			productRepository.save(productExist);
//		}
//		return productExist;
//	}
//
//	public void remove(int id) {
//		
//		Product productExist=productRepository.findById(id).get();
//		if(productExist!=null) {
//			productRepository.delete(productExist);
//		}
//	}
//
//	public List<Product> find() {
//		System.out.println("service method is called");
//		return productRepository.findAll();
//	}
//
//}
