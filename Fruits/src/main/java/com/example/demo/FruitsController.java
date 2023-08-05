package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
public class FruitsController {
	@Autowired
	FruitsRepo FruitsRepo;
	
	@GetMapping("/fruits/find")
	public Fruits findById(@RequestParam int id) {
		
		
		 Fruits fruits =FruitsRepo.findById(id).get();
		 
		 fruits.setImage(decompressBytes(fruits.getImage()));
		 
		 return fruits;
		
	}
	
	@PostMapping("/fruits/add")
	public String addProduct(@RequestParam ("dietFile") MultipartFile myFile,
			String name,
			String color,
			String price,
			String vitamins,
			String season,
			String avalability) {
		
		try {
			System.out.println(new Fruits(name,color,price,vitamins,season,avalability));
			Fruits prdImage = new Fruits(name,color,price,vitamins,season,avalability,
					compressBytes(myFile.getBytes()));
			FruitsRepo.save(prdImage);		
		}catch(Exception e) {
			
		}
		
		
		
		return "Successfully Added New Product";
		
	}
	
	@GetMapping("/fruits/delete")
	public List<Fruits> deleteDoctor(@RequestParam int id){
		
		FruitsRepo.deleteById(id);
		
		return getAllProducts();
	}
	@GetMapping("/fruits/all")
	public List<Fruits> getAllProducts(){
		
		List<Fruits> drList = new ArrayList<Fruits>();
		
		List<Fruits> resDrList = FruitsRepo.findAll();
		Fruits fruits = null;
		for(int i=0;i<resDrList.size();i++) {
			
			fruits= resDrList.get(i);
			
			fruits.setImage(decompressBytes(fruits.getImage()));
			
			drList.add(fruits);
			
		}
		
		
		return drList;
	}
	
	// compress the image bytes before storing it in the database
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


}
