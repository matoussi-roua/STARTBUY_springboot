package com.sb.STARTBUY.services;

import java.util.Set;

import com.sb.STARTBUY.entites.Products;

public interface InterfaceFavouriteListService {
	public void addProductToFavouriteList(Long iduser, Long idproduct);
	public void removeProductFromFavouriteList(Long iduser,Long idproduct);
	public Set<Products> getFavouriteList(Long iduser);
}
