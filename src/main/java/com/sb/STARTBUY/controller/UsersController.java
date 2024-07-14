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
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageUser;
import com.sb.STARTBUY.entites.Users;
import com.sb.STARTBUY.services.ImageUserService;
import com.sb.STARTBUY.services.UsersServices;
@RestController  
@CrossOrigin("*")
//en train de preparer api rest(front end n est pas dans le meme projet sinon @cotroller) 
public class UsersController {
	
	@Autowired
	UsersServices userservice;
	ImageUserService imageuserService;
	
	//@RequestMapping(method=RequestMethod.GET,value="/test/{name}")  //en generale
	@GetMapping(value="/test/{name}")  //si je suis certain que l methode est get
	public String test(@PathVariable String name) {
		return "bonjour !!!!"+name;
	}
	@PostMapping(value="/adduser")
	public Users addUser(@RequestBody Users user) {
		return userservice.addUser(user);
	}
	@DeleteMapping(value="/deleteuser/{Id}")
	public void deleteUser(@PathVariable Long Id) {
		userservice.deleteUser(Id);
	}
	@PutMapping(value="/updateuser/{id}")
	public Users updateUser(@RequestBody Users userUpdated,@PathVariable Long id) {
		return userservice.updateUser(userUpdated,id);
	}
	@GetMapping(value="/getallusers")
	public List<Users> getAllUsers() {
		return userservice.getAllUsers();
	}
	@GetMapping(value="/getuserbyid/{id}")
	public Users getUserById(@PathVariable Long id) {
		return userservice.getUserById(id);
	}
	@PostMapping(value="/existedemail")
	public boolean existedEmail(@RequestBody String email) {
		return userservice.existedEmail(email);
	}
	@PostMapping(value = "/addimagetouser/{iduser}")
	 public Users addUserToImage(@PathVariable Long iduser,@RequestParam("file") MultipartFile file) throws IOException
	 {
		 return userservice.addImageToUser(iduser,file);
	 }
	@GetMapping(value = "/getimageuser/{iduser}")
	 public ImageUser getImageUser(@PathVariable Long iduser)
	 {
		 return userservice.getImageById(iduser);
	 }
//	@PostMapping(value = "/login/{email}/{password}")
//	 public String logIn(@PathVariable String email, @PathVariable String password){	 
//		 if (!this.userservice.findByEmailAndPassword(email, password).equals(null)) {
//			 return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJvdWFAZ21haWwuY29tIiwicGFzc3dvcmQiOiJKb2huIERvZSJ9.x9NTCbs-5ACmf1mmeTwtY2ldJRiPggbMliZWkZcx96w";
//		 }
//		 else return "";
//	 }

}
