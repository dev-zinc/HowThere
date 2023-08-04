package com.howthere.app.controller;

import com.howthere.app.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/files/*")
@RequiredArgsConstructor
public class FileController {

    // TODO: 2023-08-04 파일 서비스 받기
    private final FileService fileService;

    // TODO: 2023-08-04 파일 저장로직으로 옮기기
    @Value("${file-root}")
    private final String FILE_ROOT;

//    파일 업로드
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("uploadFile") List<MultipartFile> uploadFiles) throws IOException {
        // TODO: 2023-08-04 파일 저장로직으로 옮기기
        String path = FILE_ROOT + fileService.getPath();
        List<String> saveFileNames = new ArrayList<>();
        File dir = new File(path);

        if(!dir.exists()){dir.mkdirs();}

        for (MultipartFile multipartFile : uploadFiles){
            String saveFileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(path, saveFileName));

            if(multipartFile.getContentType().startsWith("image")){
                try (FileOutputStream out = new FileOutputStream(new File(path, "t_" + saveFileName))){
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 100, 100);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return saveFileNames;
    }



//    파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException{
        return FileCopyUtils.copyToByteArray(new File(FILE_ROOT, fileName));
    }

////    파일 다운로드
//    @GetMapping
//    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
//        Resource resource = new FileSystemResource("C:/upload/" + fileName);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment;filename=" + new String(fileName.substring(fileName.indexOf("_") + 1).getBytes("UTF-8"), "ISO-8859"));
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//    }
}





















