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

import com.sb.STARTBUY.entites.ImageUser;
import com.sb.STARTBUY.entites.Users;
import com.sb.STARTBUY.repository.ImageRepository;
import com.sb.STARTBUY.repository.UsersRepository;

@Service
public class ImageUserService implements InterfaceImageService {
	@Autowired
	ImageRepository imageRep;
	@Autowired
	UsersRepository userRep;

	public ImageUser addImage(MultipartFile file) throws IOException {
		System.out.println("file name : " + file.getOriginalFilename());
		ImageUser fl = new ImageUser(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getContentType().getBytes()));
		String path = "C:/Users/Roua/Desktop/STARTBUY/angular/startbuy_ng/src/assets";
		Path pt = Paths.get(path + "/" + file.getOriginalFilename());

		Files.copy(file.getInputStream(), pt, StandardCopyOption.REPLACE_EXISTING);

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
	public ImageUser findImageUserByIdUser(Long id) {
        Users user = userRep.findById(id).orElse(null);
        if (user != null) {
            ImageUser imageUser = user.getImageuser();
            imageUser.setTailleimage(decompressBytes(imageUser.getTailleimage()));
            return imageUser;
        }
        return null;
    }

}
