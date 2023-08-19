package com.howthere.app.controller.member;

import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import com.howthere.app.service.file.house.HouseFileService;
import com.howthere.app.service.house.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/host/*")
@Slf4j
@RequiredArgsConstructor
public class HostController {

    private final HouseService houseService;
    private final HouseFileService houseFileService;

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
    public RedirectView registerHouse(HttpServletRequest req, @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("houseImg") List<MultipartFile> images) {
        final House saved = houseService.save(req);
        final List<HouseFile> fileList = houseFileService.registerHouse(saved, thumbnail, images);
        return new RedirectView("/host/inn");
    }
}
