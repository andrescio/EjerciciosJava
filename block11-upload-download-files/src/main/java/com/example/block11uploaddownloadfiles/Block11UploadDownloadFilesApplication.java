package com.example.block11uploaddownloadfiles;

import com.example.block11uploaddownloadfiles.file.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {

	@Autowired
	FileServiceImpl fileServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}
}
