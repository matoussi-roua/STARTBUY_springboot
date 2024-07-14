package com.sb.STARTBUY.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.STARTBUY.entites.ImageProduct;
@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct,Long>{

}
