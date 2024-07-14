package com.sb.STARTBUY.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageProduct;
import com.sb.STARTBUY.entites.Products;
import com.sb.STARTBUY.services.ProductsServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController  
@CrossOrigin("*")
public class ProductsController {
	@Autowired
	ProductsServices productservice;
	
	static Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@GetMapping(value="/test2/{name}")  //si je suis certain que l methode est get
	public String test2(@PathVariable String name) {
		return "bonjour !!!!"+name;
	}
	
	@PostMapping(value="/addproduct")
	public Products addProduct(@RequestBody Products product) {
		return productservice.addProduct(product);
	}
	@DeleteMapping(value="/deleteproduct/{Id}")
	public void deleteProduct(@PathVariable Long Id) {
		productservice.deleteProduct(Id);
	}
	@PutMapping(value="/updateproduct/{Id}")
	public Products updateProduct(@RequestBody Products product,@PathVariable Long Id) {
		return productservice.updateProduct(product,Id);
	}
	@GetMapping(value="/getallproducts")
	public List<Products> getAllProducts(){
		return  productservice.getAllProducts();
	}
	@GetMapping(value="/getproductbyid/{id}")
	public Products getProductById(@PathVariable Long id){
		return  productservice.getProductById(id);
	}
	@GetMapping(value="/getproductsbysyllabetitle/{syllable}")
	public List<Products> searchBySyllable(@PathVariable String syllable){
		return productservice.searchBySyllable(syllable);
	}
	@GetMapping(value="/getproductsbycategory/{category}")
	 public List<Products> searchByCategory(@PathVariable String category) {
	        return productservice.searchByCategory(category);
	    }
	@GetMapping(value="/getproductsbycategoryandsyllabe/{category}/{syllable}")
	 public List<Products> searchByCategoryAndSyllabe(@PathVariable String category,@PathVariable String syllable) {
	        return productservice.searchByCategoryAndSyllabe(category,syllable);
	    }
	@PostMapping(value = "/addimagetoproduct/{idproduct}")
	 public Products addImageToProduct(@PathVariable Long idproduct,@RequestParam("imageFile") MultipartFile file) throws IOException {
		logger.info("Original Image Byte Size - " + file.getBytes().length);
		 return productservice.addImageToProduct(idproduct,file);
	 }
	
	@GetMapping(value = "/getimageproduct/{idproduct}")
	 public ImageProduct getImageProduct(@PathVariable Long idproduct)
	 {
		System.out.println("controller");

		 return productservice.getImageById(idproduct);
	 }
	}
