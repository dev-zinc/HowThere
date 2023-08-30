package com.howthere.app.service.file;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface FileService {
    default String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy\\MM\\dd"));
    }
}
