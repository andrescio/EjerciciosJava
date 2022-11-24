package com.example.block11uploaddownloadfiles.file.service;

import com.example.block11uploaddownloadfiles.file.infraestructure.repository.FileRepository;
import com.example.block11uploaddownloadfiles.file.model.File;
import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    FileRepository fileRepository;

    // Directories of files to upload and download
    private final String uploadPath = "block11-upload-download-files/src/main/resources/uploads";

    private String downloadPath = "C:\\\\downloaded_files";

    // Method that uploads a File to the server
    @Override
    public File uploadFile(MultipartFile fileData) throws IOException {
        // Verify that file name does not already exist
        Optional<File> checkingFile = fileRepository.findByName(fileData.getOriginalFilename());
        if(!checkingFile.isEmpty())
            throw new FileAlreadyExistsException("File already exists, change the name");

        // Save the loaded file to the uploads directory that is inside the project
        java.io.File fileToSave = new java.io.File(uploadPath, fileData.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(fileToSave);
        fos.write( fileData.getBytes() );
        fos.close();

        // Creates the File with its data and saves it into the database
        File file = new File(fileData.getBytes(),fileData.getOriginalFilename(),fileData.getContentType(),new Date());
        return fileRepository.save(file);
    }

    // Method that uploads a File to the server specifying a file type through the GET request
    @Override
    public File uploadFileType(MultipartFile fileData,
                               RedirectAttributes redirectAttributes) throws IOException, InvalidTypeException {
        // Check the type of sent file
        String fileName = fileData.getOriginalFilename();
        String[] partsFileName = fileName.split("\\.");
        String fileType = partsFileName[partsFileName.length - 1];

        // Compare the file type with the specified type in the URL. If not concur, throws an InvalidTypeException
        if(!redirectAttributes.getAttribute("type").equals(fileType))
            throw new InvalidTypeException("Forbidden file type in the request");

        // If it is correct, call the uploadFile method
        return uploadFile(fileData);
    }

    // Method that download a file that exists in the database, searching by ID.
    // The file is downloaded in C:\\uploads
    @Override
    public String downloadFileById(Integer idFile) throws Exception {
        // Verify that the file exists
        Optional<File> checkingFile = fileRepository.findById(idFile);
        if(checkingFile.isEmpty())
            throw new FileNotFoundException("Not existent file, check the ID");
        File file = checkingFile.get();

        // Downloads the file using the downloadFile() method
        return downloadFile(file);
    }

    // Method that download a file that exists in the database, searching by name.
    // The file is downloaded in C:\\uploads
    @Override
    public String downloadFileByName(String nameFile) throws Exception{
        // Verify that the file exists
        Optional<File> checkingFile = fileRepository.findByName(nameFile);
        if(checkingFile.isEmpty())
            throw new FileNotFoundException("Not existent file, check the name");
        File file = checkingFile.get();

        // Downloads the file using the downloadFile() method
        return downloadFile(file);
    }

    // Set the downloadPath with a path passed by parameter
    @Override
    public String setDownloadPath(String downloadPath) throws Exception {
        // Check if the path exists
        Path path = Path.of(downloadPath);
        if(!Files.exists(path))
            throw new FileNotFoundException("Not existent path, check it");
        this.downloadPath = downloadPath;
        return "Path set successfully to "+downloadPath;
    }

    // Method that downloads a file passed by parameter
    public String downloadFile(File file) throws IOException {
        // If the directory does not exist, creates it. If exist, does nothing.
        Files.createDirectories(Paths.get(downloadPath));

        // Save the file into the directory
        java.io.File fileToSave = new java.io.File(downloadPath, file.getName());
        FileOutputStream fos = new FileOutputStream(fileToSave);
        fos.write( file.getFile() );
        fos.close();
        return "Saved succesfully";
    }
}