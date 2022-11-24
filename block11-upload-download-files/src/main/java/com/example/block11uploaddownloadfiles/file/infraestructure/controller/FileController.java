package com.example.block11uploaddownloadfiles.file.infraestructure.controller;

import com.example.block11uploaddownloadfiles.file.model.File;
import com.example.block11uploaddownloadfiles.file.service.FileServiceImpl;
import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileServiceImpl fileServiceImpl;

    // Pass a received file with a POST request to the service uploadFile() method
    @PostMapping(value="/upload",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public File uploadFile(@RequestParam MultipartFile fileData) throws IOException {
        return fileServiceImpl.uploadFile(fileData);
    }

    // Pass a received file and permitted extension added to the redirectAttributes
    // with a POST request to the uploadFileType() method
    @PostMapping(value="/upload/{type}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public File uploadFileType(@PathVariable String type,
                               @RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException, InvalidTypeException {
        redirectAttributes.addAttribute("type",type);
        return fileServiceImpl.uploadFileType(file, redirectAttributes);
    }

    // Pass a received id with a GET request to the service dowloadFileById() method
    @GetMapping("/download/{id}")
    public String downloadFileById(@PathVariable Integer id) throws Exception {
        return fileServiceImpl.downloadFileById(id);
    }

    // Pass a received name with a GET request to the service dowloadFileByName() method
    @GetMapping("/download/name/{name}")
    public String downloadFileByName(@PathVariable String name) throws Exception {
        return fileServiceImpl.downloadFileByName(name);
    }

    // Set the path to the downloaded filed
    @GetMapping("/setPath")
    public String setDownloadPath(@RequestParam String path) throws Exception {
        return fileServiceImpl.setDownloadPath(path);
    }
}
