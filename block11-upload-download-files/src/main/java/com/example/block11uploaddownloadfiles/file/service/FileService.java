package com.example.block11uploaddownloadfiles.file.service;

import com.example.block11uploaddownloadfiles.file.model.File;
import com.sun.jdi.InvalidTypeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;


public interface FileService {
    File uploadFile(MultipartFile fileData) throws IOException;

    File uploadFileType(MultipartFile fileData, RedirectAttributes redirectAttributes) throws IOException, InvalidTypeException;

    String downloadFileById(Integer idFile) throws Exception;

    String downloadFileByName(String nameFile) throws Exception;

    String setDownloadPath(String downloadPath) throws Exception;
}
