package com.howthere.app.controller.rest;

import com.howthere.app.service.file.FileRestService;
import com.howthere.app.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files/api/*")
public class FileRestController {
    @Value("${file.directory}")

    @GetMapping("upload")
    public List<String> uploadFile(List<MultipartFile> uploadFile, @RequestParam(value = "fileType") FileType fileType, Long id) {
        String path = "" + FileRestService.getPath();
    }
}
