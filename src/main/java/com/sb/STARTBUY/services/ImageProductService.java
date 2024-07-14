package com.sb.STARTBUY.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sb.STARTBUY.entites.ImageProduct;
import com.sb.STARTBUY.entites.Products;
import com.sb.STARTBUY.repository.ImageProductRepository;
import com.sb.STARTBUY.repository.ProductsRepository;

@Service
public class ImageProductService implements InterfaceImageProductService {
	@Autowired
	ImageProductRepository imageRep;
	@Autowired
	ProductsRepository productRep;

	@Override
	public ImageProduct addImage(MultipartFile file) throws IOException {
		System.out.println("file name : " + file.getOriginalFilename());
		ImageProduct fl = new ImageProduct(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
//		String path = "C:/Users/Roua/Desktop/STARTBUY/angular/startbuy_ng/src/assets";
//		Path pt = Paths.get(path + "/" + file.getOriginalFilename());
//
//		Files.copy(file.getInputStream(), pt, StandardCopyOption.REPLACE_EXISTING);

		return imageRep.save(fl);
	};

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
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

	@Override
	public ImageProduct findImageProductByIdProduct(Long id) {
		System.out.println("imageproduct service");

		Products product = productRep.findById(id).orElse(null);
		if (product != null) {
			System.out.println("hello");
			ImageProduct imageProduct = product.getImageproduct();
			imageProduct.setTailleimage(decompressBytes(imageProduct.getTailleimage()));
			return imageProduct;
		}
		return null;
	}
}