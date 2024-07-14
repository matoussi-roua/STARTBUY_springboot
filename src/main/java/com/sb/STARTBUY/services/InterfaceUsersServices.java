package com.sb.STARTBUY.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageUser;
import com.sb.STARTBUY.entites.Users;

public interface InterfaceUsersServices {
	public Users addUser(Users users);

	public void deleteUser(Long Id);

	public Users updateUser(Users userUpdated, Long id);

	public List<Users> getAllUsers();

	public Users getUserById(Long id);

	boolean existedEmail(String email);

	Users addImageToUser(Long iduser, MultipartFile file) throws IOException;

	ImageUser getImageById(Long iduser);


}
