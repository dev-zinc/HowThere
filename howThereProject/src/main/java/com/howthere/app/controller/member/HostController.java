package com.howthere.app.controller.member;

import com.howthere.app.service.member.HostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/host/*")
@Slf4j
@RequiredArgsConstructor
public class HostController {

    private final HostService hostService;

    @Value("${file-root}")
    private String FILE_ROOT;

    //    http://localhost:10000/host/write
    @GetMapping("write")
    public void write() {

    }

    //    http://localhost:10000/host/inn
    @GetMapping("inn")
    public void inn() {

    }

    //    http://localhost:10000/host/hosting
    @GetMapping("hosting")
    public void hosting() {
        ;
    }

    @PostMapping("write")
    public void registerHouse(MultipartHttpServletRequest req) {
        final String houseName = req.getParameter("houseName");
        final String address = req.getParameter("address");
        final String addressDetail = req.getParameter("addressDetail");
        final String summernote = req.getParameter("content");
        final String guestCnt = req.getParameter("maxGuestCnt");
        final String petCnt = req.getParameter("maxPetCnt");
        req.getFileNames().forEachRemaining(System.out::println);
        final List<MultipartFile> files = req.getFiles("files");
        files.forEach(v -> {
            System.out.println(v.getOriginalFilename());
        });
        final List<MultipartFile> images = req.getFiles("houseImg");
        images.forEach(v -> {
            System.out.println(v.getOriginalFilename());
        });
    }

    @PostMapping("upload")
    public String upload(@RequestParam("uploadFile") List<MultipartFile> uploadFiles) {
        uploadFiles.forEach(v -> {
            System.out.println(v.getOriginalFilename());
        });
//        String path = FILE_ROOT;
//        List<String> saveFileNames = new ArrayList<>();
//        File dir = new File(path);
//        try {
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//
//            for (MultipartFile multipartFile : uploadFiles) {
//                String saveFileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
//                multipartFile.transferTo(new File(path, saveFileName));
//
//                if (multipartFile.getContentType().startsWith("image")) {
//                    try (FileOutputStream out = new FileOutputStream(new File(path, "t_" + saveFileName))) {
//                        Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 100, 100);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return saveFileNames;
        return "OK";
    }
}
