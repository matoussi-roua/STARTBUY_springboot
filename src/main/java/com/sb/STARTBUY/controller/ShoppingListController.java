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
import com.sb.STARTBUY.services.ShoppingListServices;
@CrossOrigin("*")
@RestController
public class ShoppingListController {
@Autowired
 ShoppingListServices shoppingservice ;
@PutMapping(value="/addtoshop/{iduser}/{idproduct}")
public void addProductToShoppingList(@PathVariable Long iduser,@PathVariable Long idproduct) {
	shoppingservice.addProductToShoppingList(iduser, idproduct);
}
@DeleteMapping(value="/removefromshop/{iduser}/{idproduct}")
public void removeProductFromShoppingList(@PathVariable Long iduser,@PathVariable Long idproduct) {
	shoppingservice.removeProductFromShoppingList(iduser, idproduct);
}
@GetMapping(value = "/getshoppinglist/{iduser}")
public Set<Products> getShoppingList(@PathVariable Long iduser){
	return shoppingservice.getShoppingList(iduser);
	
}
@DeleteMapping(value="/removeallfromshop/{iduser}")
public void removeAllProductFromShoppingList(@PathVariable Long iduser) {
	shoppingservice.removeAllProductFromShoppingList(iduser);
}
}
