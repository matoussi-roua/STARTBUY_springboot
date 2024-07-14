package com.sb.STARTBUY.services;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageProduct;
import com.sb.STARTBUY.entites.Products;

public interface InterfaceProductsServices {
	public Products addProduct(Products product);
	public Products updateProduct(Products product, Long id);
	public void deleteProduct(Long Id);
	public List<Products> getAllProducts();
	public Products getProductById(Long id);
	public List<Products> searchBySyllable(String syllable);
	List<Products> searchByCategory(String category);
	public List<Products> searchByCategoryFromList(List<Products> prod, String category);
	public List<Products> searchByCategoryAndSyllabe(String category, String syllable);
	Products addImageToProduct(Long idProduct, MultipartFile file) throws IOException;
	ImageProduct getImageById(Long idproduct);
}
