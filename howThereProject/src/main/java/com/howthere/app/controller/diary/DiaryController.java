package com.howthere.app.controller.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.service.diary.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/diary/*")
public class DiaryController {
    private final DiaryService diaryService;

    //http://localhost:10000/diary/list
    @GetMapping("list")
    public void list(Model model, @PageableDefault(page = 0, size = 8) Pageable pageable) {
        final Page<Diary> pagination = diaryService.getList(pageable);
        model.addAttribute("pagination", pagination);
//        model.addAttribute("test", 1);
    }

    @GetMapping("api/list")
    @ResponseBody
    public Slice<DiaryDTO> list(@PageableDefault(page = 0, size = 8) Pageable pageable) {
        Slice<DiaryDTO> diarys = diaryService.getListBySlice(pageable);

        return diaryService.getListBySlice(pageable);
    }

    //http://localhost:10000/diary/article
    @GetMapping("article/{id}")
    public String article(@PathVariable Long id, Model model) {
        diaryService.getDiary(id).ifPresent((diary) -> {
            model.addAttribute("diary", diary);
        });
        return "/diary/article";
    }

    //http://localhost:10000/diary/write
    @GetMapping("write")
    public void goToWriteForm(Model model) {
        model.addAttribute("diary", new DiaryDTO());
    }

    @PostMapping("write")
    public RedirectView write(DiaryDTO diaryDTO) {
        diaryDTO.setMemberId(1L);
        diaryDTO.setHouseId(3L);
        diaryService.write(diaryDTO);
        return new RedirectView("/diary/list");
    }

    //write 페이지에서 modify
//    //http://localhost:10000/diary/modify
//    @GetMapping("modify")
//    public void goToModifyForm() {;}
//
//    @PostMapping("modify")
//    public void modify() {;}
}
