package ru.itis.semestralwork.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

public interface FilesStorageService {

    String saveFile(MultipartFile file);

    void writeFileToResponse(String fileName, HttpServletResponse response);

    File downloadFile(String filename);
}
