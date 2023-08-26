package com.howthere.app.controller.member;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.entity.house.House;
import com.howthere.app.service.file.house.HouseFileService;
import com.howthere.app.service.house.HouseService;
import com.howthere.app.service.program.ProgramService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/host/*")
@Slf4j
@RequiredArgsConstructor
public class HostController {

    private final HouseService houseService;
    private final HouseFileService houseFileService;
    private final ProgramService programService;

    //    http://localhost:10000/host/write
    @GetMapping("write")
    public ModelAndView write(ModelAndView mv,
        @RequestParam(value = "id", required = false) Long id) {
        mv.setViewName("/host/write");
        if (!Objects.isNull(id)) {
            final HouseDTO house = houseService.getHouse(id);
            mv.setViewName("/host/edit");
            mv.addObject("house", house);
        }
        return mv;
    }

    //    http://localhost:10000/host/inn
    @GetMapping("inn")
    public ModelAndView inn(HttpSession session,
        @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
        ModelAndView mv) {

        // TODO: 2023/08/20 로그인 작업 완료 시 수정 예정
        final Page<HouseDTO> pagination = houseService.getMyHouses(pageable, 1L);
        mv.addObject("pagination", pagination);
        mv.setViewName("/host/inn");
        return mv;
    }

    //    http://localhost:10000/host/hosting
    @GetMapping("hosting")
    public void hosting(@RequestParam(value = "id") Long id, Model model) {
        final HouseDTO house = houseService.getHouse(id);
        model.addAttribute("house", house);
    }

    @PostMapping("hosting")
    public void registerProgram(@RequestBody ProgramDTO dto) {
        programService.registerProgram(dto);
        // TODO: 2023/08/25 체크인 체크아웃 기간 확인해서 겹치면 코드 리턴
    }

    @PostMapping("write")
    public RedirectView registerHouse(HttpServletRequest req,
        @RequestParam("thumbnail") MultipartFile thumbnail,
        @RequestParam("houseImg") List<MultipartFile> images) {
        try {
            final House saved = houseService.registerHouse(req);
            houseFileService.registerHouse(saved, thumbnail, images);
            // TODO: 2023/08/19 Exception 페이지 구현
            return new RedirectView("/host/inn");
        } catch (IOException e) {
            return new RedirectView("/host/write");
        }
    }

    @GetMapping("load-image")
    public ResponseEntity<Resource> display(@RequestParam("filePath") String filePath) {
        final Resource resource = new FileSystemResource(filePath);
        if (!resource.exists()) {
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }
        final HttpHeaders header = new HttpHeaders();
        try {
            final Path path = Paths.get(filePath);
            header.add("Content-type", Files.probeContentType(path));
            return new ResponseEntity<>(resource, header, HttpStatus.OK);
        } catch (IOException e) {
            log.error("load image error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
