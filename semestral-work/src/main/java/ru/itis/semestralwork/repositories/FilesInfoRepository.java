package ru.itis.semestralwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.semestralwork.models.FileInfo;

public interface FilesInfoRepository extends JpaRepository<FileInfo, Long> {

    FileInfo findByAndStorageFileName(String name);
}
