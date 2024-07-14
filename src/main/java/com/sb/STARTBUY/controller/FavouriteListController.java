package com.sb.STARTBUY.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.STARTBUY.entites.Products;
import com.sb.STARTBUY.services.FavouriteListService;
@RestController 
@CrossOrigin("*")

public class FavouriteListController {
	@Autowired
	FavouriteListService favouriteservice;
	
	@PutMapping(value="/addtofavourite/{iduser}/{idproduct}")
	public void addProductToFavouriteList(@PathVariable Long iduser,@PathVariable Long idproduct) {
		favouriteservice.addProductToFavouriteList(iduser, idproduct);
	}
	@DeleteMapping(value="/removefromfavourite/{iduser}/{idproduct}")
	public void removeProductFromFavouriteList(@PathVariable Long iduser,@PathVariable Long idproduct) {
		favouriteservice.removeProductFromFavouriteList(iduser, idproduct);
	}
	@GetMapping(value = "/getfavouritelist/{iduser}")
	public Set<Products> getFavouriteList(@PathVariable Long iduser){
		return favouriteservice.getFavouriteList(iduser);
	}
}
