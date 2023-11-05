package com.ute.shop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import com.ute.shop.config.StorageProperties;
import com.ute.shop.exception.StorageExeception;
import com.ute.shop.exception.StorageFileNotFoundExeception;
import com.ute.shop.service.StorageService;
@Service
public class FileSystemStorageServiceImpl implements StorageService{
	private Path rootLocation = null;
	@Override
	public String getStoredFileName (MultipartFile file,String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}
	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	@Override
	public void store(MultipartFile file,String storedFileName) {
		try {
			if(file.isEmpty()) {
				throw new StorageExeception("Failed to store Empty File ");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();
			if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageExeception("Cannot store file outside current directory");
			}
			try (InputStream inputsteam = file.getInputStream()){
				Files.copy(inputsteam, destinationFile,StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageExeception("Failed to store file " , e);
		}
	}
	@Override
	public org.springframework.core.io.Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileNotFoundExeception("Could not read file " + fileName);
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageFileNotFoundExeception("Could not read file " + fileName);
		}
	}
	@Override
	public Path load(String fileName) {
		return rootLocation.resolve(fileName);
	}
	@Override
	public void delete(String storedFile) throws IOException {
		Path destinationFile = rootLocation.resolve(Paths.get(storedFile)).normalize().toAbsolutePath();
		Files.delete(destinationFile);
	}
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageExeception("Could not initialize storage " , e);
		}
	}
	
}
