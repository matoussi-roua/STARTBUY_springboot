package com.sb.STARTBUY.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
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
public class FavouriteListService implements InterfaceFavouriteListService{

	@Autowired
	ProductsRepository productRep;
	@Autowired
	UsersRepository userRep;
	@Override
	public void addProductToFavouriteList(Long iduser, Long idproduct) {
		Users user = userRep.findById(iduser).get();
		Products product = productRep.findById(idproduct).get();
		addProductToFavourite(user, product);
	}
	private void addProductToFavourite(Users user, Products product) {
		user.getListfavourite().add(product);
//		product.getListusers().add(user);
//		updateUser(user,user.getIduser() );
		userRep.save(user);
//		productRep.save(product);
	}
	@Override
	public void removeProductFromFavouriteList(Long iduser, Long idproduct) {
		Users user = userRep.findById(iduser).get();
		Products product = productRep.findById(idproduct).get();
		removeProductFromFavourite(user, product);
	}

	private void removeProductFromFavourite(Users user, Products product) {
		user.getListfavourite().remove(product);
		userRep.save(user);
	}

	@Override
	public Set<Products> getFavouriteList(Long iduser) {
//		Users user = userRep.findById(iduser).get();
//		Set<Products> products = user.getListfavourite();
//		return products;
		
		return convertImageList(userRep.favouriteListById(iduser));
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
