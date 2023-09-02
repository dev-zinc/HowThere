package com.howthere.app.controller.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.service.diary.DiaryService;
import com.howthere.app.service.diary.like.DiaryLikeService;
import com.howthere.app.service.diary.reply.DiaryReplyService;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/diary/*")
public class DiaryController {
    private final DiaryService diaryService;
    private final DiaryReplyService diaryReplyService;
    private final DiaryLikeService diaryLikeService;
    private final HttpSession session;

    //http://localhost:10000/diary/list
    @GetMapping("list")
    public void list(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "") String order, Model model) {
        model.addAttribute("member", session.getAttribute("member"));
        model.addAttribute("keyword", keyword);
        model.addAttribute("order", order);
    }

    @GetMapping("api/list")
    @ResponseBody
    public Slice<DiaryDTO> list(@PageableDefault(page = 0, size = 8) Pageable pageable, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "") String order) {
        return diaryService.getListBySlice(pageable, keyword, order);
    }

    //http://localhost:10000/diary/article
    @GetMapping("article/{id}")
    public String article(@PathVariable Long id, Model model) {
        model.addAttribute("member", session.getAttribute("member"));
        diaryService.updateViewCount(id);
        diaryService.getDiary(id).ifPresent((diary) -> {
            model.addAttribute("diary", diary);
        });
        return "/diary/article";
    }

    //http://localhost:10000/diary/write
    @GetMapping("write/{houseId}")
    public String goToWriteForm(@PathVariable Long houseId, Model model) {
//        Long memberId = (Long) session.getAttribute("id");
//        Long memberId = 1L;
//        model.addAttribute("memberId", memberId);
        model.addAttribute("member", session.getAttribute("member"));
        model.addAttribute("houseId", houseId);
        model.addAttribute("diary", new DiaryDTO());
        return "/diary/write";
    }

    @PostMapping("write")
    public RedirectView write(DiaryDTO diaryDTO) {
        log.info(diaryDTO.toString());
        Long id = diaryService.write(diaryDTO);
        return new RedirectView("/diary/article/" + id);
    }

    //write 페이지에서 modify
    //http://localhost:10000/diary/modify
    @GetMapping("modify/{id}")
    public String goToModifyForm(@PathVariable Long id, Model model) {
        model.addAttribute("member", session.getAttribute("member"));
        diaryService.getDiary(id).ifPresent((diary) -> {
            model.addAttribute("diary", diary);
        });
        return "/diary/modify";
    }

    @PostMapping("modify")
    public RedirectView modify(DiaryDTO diaryDTO) {
        diaryService.update(diaryDTO);
        return new RedirectView("/diary/article/" + diaryDTO.getId());
    }

    @GetMapping("delete/{id}")
    public RedirectView delete(@PathVariable Long id, Model model) {
        model.addAttribute("member", session.getAttribute("member"));
        diaryReplyService.removeByDiaryId(id);
        diaryLikeService.removeByDiaryId(id);
        diaryService.remove(id);
        return new RedirectView("/diary/list");
    }
}
