package com.sb.STARTBUY.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageUser;

public interface InterfaceImageService {
	public ImageUser addImage(MultipartFile file) throws IOException;

	public ImageUser findImageUserByIdUser(Long id);
}
