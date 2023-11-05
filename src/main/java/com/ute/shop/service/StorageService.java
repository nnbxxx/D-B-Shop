package com.ute.shop.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void delete(String storedFile) throws IOException;

	Path load(String fileName);

	org.springframework.core.io.Resource loadAsResource(String fileName);

	void store(MultipartFile file, String storedFileName);

	String getStoredFileName(MultipartFile file, String id);

}
