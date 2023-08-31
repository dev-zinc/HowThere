package com.howthere.app.service.file;

import com.howthere.app.domain.file.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public interface FileService {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd/");

    default String getPath(){
        return LocalDate.now().format(format);
    }

    default FileDTO toDTO(MultipartFile multipartFile) {
        return FileDTO.builder()
                .fileSize(multipartFile.getSize())
                .fileUuid(UUID.randomUUID().toString())
                .fileName(multipartFile.getOriginalFilename())
                .filePath(getPath())
                .build();
    }
}
