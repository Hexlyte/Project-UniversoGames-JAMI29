//package org.generation.italy.universogames.controller;
//
//import org.generation.italy.universogames.service.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//// NON DOVREBBE SERVIRCI, ABBIAMO CREATO I METODI NECESSARI IN ControllerPosts
//
//@RestController
//@RequestMapping("/file")
//public class FileUploadController {
//
//	private FileStorageService fs;
//	
//	@Autowired
//	public FileUploadController(FileStorageService fs) {
//		this.fs = fs;
//	}
//	
//	@PostMapping("/upload")
//	public String uploadFile(@RequestParam MultipartFile file) {
//		if(!file.isEmpty()) {
//			return fs.saveFile(file);
//		}
//		return "Non hai inserito il file.";
//	}
//	
//	@DeleteMapping("/delete/{fileName}")
//	public void deleteFile(@PathVariable String fileName) {
//		fs.deleteFile(fileName);
//	}
//}
