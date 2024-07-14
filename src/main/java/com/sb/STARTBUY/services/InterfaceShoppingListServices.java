package com.sb.STARTBUY.services;

import java.util.Set;

import com.sb.STARTBUY.entites.Products;

public interface InterfaceShoppingListServices {

	public Set<Products> getShoppingList(Long iduser);

	public void removeProductFromShoppingList(Long iduser, Long idproduct);

	public void addProductToShoppingList(Long iduser, Long idproduct);

	public void removeAllProductFromShoppingList(Long iduser);
}
