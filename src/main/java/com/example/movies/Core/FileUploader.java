package com.example.movies.Core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileUploader {

	private static String path = System.getProperty("user.home");
	private static String folderPath = path + "/files/";
	private static String filePath;
	
	private static void createMainFolder() {
		File mainFolder = new File(folderPath);
		if(!mainFolder.exists()) {
			mainFolder.mkdir();
			log.info("Main folder was created in the source path");
		}
	}
	
	private static void createFolder(String folderName) {
		filePath = folderPath + "/"+folderName+"/";
		File folder = new File(filePath);
		if(!folder.exists()) {
			folder.mkdir();
			log.info("Folder : " + folderName + " was created");
		}
		
	}
	
	public void registerFile(
			MultipartFile file,
			String folderName,
			Long id) throws Exception{
		if(!file.isEmpty()) {
			createMainFolder();
			createFolder(folderName);
			file.transferTo(new File(filePath + id));
			log.info("File " + file.getOriginalFilename() + " has been created ");
		}else {
			log.warn("File not found!");
		}
	}
	
	public void updateFile(
			MultipartFile file,
			String folderName,
			Long id) throws Exception{
		if(!file.isEmpty()) {
			createMainFolder();
			createFolder(folderName);
			Path fileUpdate = Paths.get(filePath + id);
			file.transferTo(fileUpdate);
			log.info("File " + file.getOriginalFilename() + " has been updated");
		}
 	} 
	
	public File getFile(Long id) {
		File file = new File(filePath + id);
		if(file.exists()) {
			return file;
		}else {
			return null;
		}
	}
	
	public void deleteFile(Long id, String folder) {
		filePath = folderPath + "/"+folder+"/" + id;
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
			log.warn("File with id : " + id + " into " + folder + " has been deleted");
		}else {
			log.warn("This file doesn't exists");
		}
	}
	
}

















