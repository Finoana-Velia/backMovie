package com.example.movies.Core;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUploader {

	private String folderName;
	private String fileName;
	
	private static void createFolder() {
		
	}
	
	public static void registerFile(MultipartFile file) {
		if(!file.isEmpty()) {
			createFolder();
			File fileRegister = new File("File path");
		}
	}
	
	public static void updateFile(MultipartFile file) {
		if(!file.isEmpty()) {
			createFolder();
			System.out.println("Path file folder");
		}
 	} 
	
}
