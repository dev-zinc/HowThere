package com.howthere.app.service.file;

import com.howthere.app.entity.file.FileEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface FileRestService<E extends FileEntity> {
    void saveFile(E e);

    static String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
