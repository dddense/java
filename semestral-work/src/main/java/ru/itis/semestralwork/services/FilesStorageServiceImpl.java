package ru.itis.semestralwork.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semestralwork.models.FileInfo;
import ru.itis.semestralwork.repositories.FilesInfoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    @Autowired
    private FilesInfoRepository filesInfoRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public String saveFile(MultipartFile uploadingFile) {

        String storageName = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(uploadingFile.getOriginalFilename());

        FileInfo file = FileInfo.builder()
                .type(uploadingFile.getContentType())
                .originalName(uploadingFile.getOriginalFilename())
                .storageFileName(storageName)
                .url(storagePath + "\\" + storageName)
                .size(uploadingFile.getSize())
                .build();

        try {
            Files.copy(uploadingFile.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        filesInfoRepository.save(file);

        return file.getStorageFileName();
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {

        FileInfo fileInfo = filesInfoRepository.findByAndStorageFileName(fileName);
        response.setContentType(fileInfo.getType());

        try {
            IOUtils.copy(new FileInputStream(fileInfo.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public File downloadFile(String filename) {

        FileInfo fileInfo = filesInfoRepository.findByAndStorageFileName(filename);
        File file = new File(storagePath + "\\" + fileInfo.getStorageFileName());

        return file;
    }
}
