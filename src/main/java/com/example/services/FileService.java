package com.example.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	private final static String FILE_FOLDER = "files";
	
	public Path getPath(String file) {
		return Paths.get(FILE_FOLDER).resolve(file).toAbsolutePath();
	}
	
	
	public String copy(MultipartFile file) throws IOException{
		String name = file.getOriginalFilename();
		Path rootPath = getPath(name);			
		Files.copy(file.getInputStream(), rootPath);			
		
		return name;
	}

}
