package com.sb.STARTBUY.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.STARTBUY.entites.Products;
import com.sb.STARTBUY.entites.Users;
import com.sb.STARTBUY.repository.ProductsRepository;
import com.sb.STARTBUY.repository.UsersRepository;

@Service
public class ShoppingListServices implements InterfaceShoppingListServices {
	@Autowired
	ProductsRepository productRep;
	@Autowired
	UsersRepository userRep;
	@Override
	public void addProductToShoppingList(Long iduser, Long idproduct) {
		Users user = userRep.findById(iduser).get();
		Products product = productRep.findById(idproduct).get();
		addProductToShopping(user, product);
	}
	private void addProductToShopping(Users user, Products product) {
		user.getShoppinglist().add(product);
//		product.getListusers().add(user);
//		updateUser(user,user.getIduser() );
		userRep.save(user);
//		productRep.save(product);
	}
	@Override
	public void removeProductFromShoppingList(Long iduser, Long idproduct) {
		Users user = userRep.findById(iduser).get();
		Products product = productRep.findById(idproduct).get();
		removeProductFromShopping(user, product);
	}

	private void removeProductFromShopping(Users user, Products product) {
		user.getShoppinglist().remove(product);
		userRep.save(user);
	}

	@Override
	public Set<Products> getShoppingList(Long iduser) {
//		Users user = userRep.findById(iduser).get();
//		Set<Products> products = user.getListfavourite();
//		return products;
		
		return convertImageList(userRep.shoppingListById(iduser));
	}
	@Override
	public void removeAllProductFromShoppingList(Long iduser) {
		Users user = userRep.findById(iduser).get();
		user.getShoppinglist().clear();
		userRep.save(user);
	}
	private Set<Products> convertImageList(Set<Products> products) {
		products.forEach(product -> {
			byte[] picByte = product.getImageproduct().getTailleimage();
			product.getImageproduct().setTailleimage(decompressBytes(picByte));
		});
		return products;
	}
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		if(data==null) {
			return null;
		}
		else {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
	}

}
