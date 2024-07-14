package com.sb.STARTBUY.services;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageUser;
import com.sb.STARTBUY.entites.Users;
import com.sb.STARTBUY.repository.ProductsRepository;
import com.sb.STARTBUY.repository.UsersRepository;

@Service
public class UsersServices implements InterfaceUsersServices {

	@Autowired
	UsersRepository userRep;
	@Autowired
	ProductsRepository productRep;
	@Autowired
	ImageUserService imageuserSrv;

	@Override
	public Users addUser(Users user) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		user.setPassword(encoder.encode(user.getPassword()));
		return userRep.save(user);
	}

	@Override
	public void deleteUser(Long Id) {
		userRep.deleteById(Id);
	}

	@Override
	public Users updateUser(Users userUpdated, Long id) {
		Users userToUpdate = userRep.findById(id).get();
		userToUpdate.setFirstName(userUpdated.getFirstName());
		userToUpdate.setLastName(userUpdated.getLastName());
		userToUpdate.setCity(userUpdated.getCity());
		userToUpdate.setCountry(userUpdated.getCountry());
		userToUpdate.setEmail(userUpdated.getEmail());
		userToUpdate.setPassword(userUpdated.getPassword());
		userToUpdate.setPhone(userUpdated.getPhone());
		userToUpdate.setPostCode(userUpdated.getPostCode());
		userToUpdate.setRole(userUpdated.getRole());

		return userRep.save(userToUpdate);
	}

	@Override
	public List<Users> getAllUsers() {
		return userRep.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		Users user = userRep.findById(id).get();
		return userRep.save(user);

	}

	@Override
	public boolean existedEmail(String email) {
		List<Users> emails = userRep.findByEmail(email);
		return !emails.isEmpty();
	}

	@Override
	public Users addImageToUser(Long iduser, MultipartFile file) throws IOException {
		Users user = userRep.findById(iduser).get();
		ImageUser imageuser = imageuserSrv.addImage(file);
		user.setImageuser(imageuser);
		return userRep.save(user);
	}

	@Override
	public ImageUser getImageById(Long iduser) {
		return imageuserSrv.findImageUserByIdUser(iduser);
		// Users user=userRep.findById(iduser).get();
		// System.out.println("hello");
		// return user.getImageuser();
	}

	public Users findByEmailAndPassword(String email, String password) {
		return userRep.findByEmailAndPassword(email, password);
	}
}
