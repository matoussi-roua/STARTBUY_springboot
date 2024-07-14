package com.sb.STARTBUY.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageProduct;
import com.sb.STARTBUY.entites.Products;
import com.sb.STARTBUY.repository.ProductsRepository;

@Service
public class ProductsServices implements InterfaceProductsServices {

	@Autowired
	ProductsRepository productRep;
	@Autowired
	ImageProductService imageproductSrv;

	@Override
	public Products addProduct(Products product) {
		return productRep.save(product);
	}

	@Override
	public void deleteProduct(Long Id) {
		productRep.deleteById(Id);
	}

	@Override
	public Products updateProduct(Products productUpdated, Long id) {
		Products productToUpdate = productRep.findById(id).get();
		productToUpdate.setTitle(productUpdated.getTitle());
		productToUpdate.setCategory(productUpdated.getCategory());
		productToUpdate.setDescription(productUpdated.getDescription());
		productToUpdate.setPrice(productUpdated.getPrice());
		return productRep.save(productToUpdate);
	}

	@Override
	public List<Products> getAllProducts() {
		return convertImageList(productRep.findAll());
	}
	
	private List<Products> convertImageList(List<Products> products) {
		products.forEach(product -> {
			byte[] picByte = product.getImageproduct().getTailleimage();
			product.getImageproduct().setTailleimage(decompressBytes(picByte));
		});
		return products;
	}
	@Override
	public Products getProductById(Long id) {
		Products product = productRep.findById(id).get();
		byte[] picByte = product.getImageproduct().getTailleimage();
		product.getImageproduct().setTailleimage(decompressBytes(picByte));
		return product;
	}

	@Override
	public List<Products> searchBySyllable(String syllable) {
		return productRep.findByTitleContaining(syllable);
	}

	@Override
	public List<Products> searchByCategory(String category) {
		return productRep.findByCategory(category);
	}

	@Override
	public List<Products> searchByCategoryFromList(List<Products> prod, String category) {
		List<Products> filteredProducts = new ArrayList<>();
		for (Products product : prod) {

			if (product.getCategory().equals(category)) {

				filteredProducts.add(product);
			}
		}
		return filteredProducts;
	}

	@Override
	public List<Products> searchByCategoryAndSyllabe(String category, String syllable) {
		if (!category.equals("Categories") && !syllable.equals("null")) {
			return convertImageList(searchByCategoryFromList(searchBySyllable(syllable), category));
		} else if (category.equals("Categories") && !syllable.equals("null")) {
			return convertImageList(searchBySyllable(syllable));
		} else if (!category.equals("Categories") && syllable.equals("null")) {
			return convertImageList(searchByCategory(category));
		} else
			return getAllProducts();

	}

	@Override
	public Products addImageToProduct(Long idProduct, MultipartFile file) throws IOException {
		Products product = productRep.findById(idProduct).get();
		ImageProduct imageproduct = imageproductSrv.addImage(file);
		product.setImageproduct(imageproduct);
		
		return productRep.save(product);
	}

	@Override
	public ImageProduct getImageById(Long idproduct) {
		System.out.println("service product");

		return imageproductSrv.findImageProductByIdProduct(idproduct);

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
