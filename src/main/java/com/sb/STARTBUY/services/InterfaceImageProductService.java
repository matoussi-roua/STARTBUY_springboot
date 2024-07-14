package com.sb.STARTBUY.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageProduct;

public interface InterfaceImageProductService {

	ImageProduct addImage(MultipartFile file) throws IOException;

	public ImageProduct findImageProductByIdProduct(Long id);

}
