package com.sb.STARTBUY.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.STARTBUY.entites.Products;

public interface ProductsRepository extends JpaRepository<Products,Long> {

	List<Products> findByTitleContaining(String syllable);

	List<Products> findByCategory(String category);

}