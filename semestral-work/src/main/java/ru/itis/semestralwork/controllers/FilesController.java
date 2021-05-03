package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semestralwork.services.FilesStorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
public class FilesController {

    @Autowired
    private FilesStorageService filesStorageService;

    @GetMapping("/files/upload")
    public String getFilesUploadPage() {

        return "file_upload_page";
    }

    @PostMapping("/files")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {

        String filePath = filesStorageService.saveFile(file);

        return ResponseEntity.ok().body(filePath);
    }

    @GetMapping("/files/{file-name}")
    public ResponseEntity<Resource> getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {

        System.out.println(fileName);
        File file = filesStorageService.downloadFile(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(inputStreamResource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
