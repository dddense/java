package ru.itis.mimimeter.impl.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageController {

    @Value("${storage.path}")
    private String storagePath;

    @GetMapping(value = "/data/img/cats/{image-name}")
    public ResponseEntity<Resource> getImage(@PathVariable("image-name") String imgName) {

        try {
            InputStream is = new FileInputStream(storagePath + imgName);
            byte[] bytes = IOUtils.toByteArray(is);
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
            return ResponseEntity.ok()
                    .contentLength(bytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(byteArrayResource);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
