package com.howthere.app.controller.diary;

import com.howthere.app.domain.diary.DiaryLikeDTO;
import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.service.diary.like.DiaryLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/diary-likes/*")
public class DiaryLikeController {
    private final DiaryLikeService diaryLikeService;

    @PostMapping("like")
    public void like(@RequestBody DiaryLikeDTO diaryLikeDTO){
        diaryLikeService.like(diaryLikeDTO);
    }

    @GetMapping("get/{memberId}/{diaryId}")
    public Boolean get(@PathVariable Long memberId, @PathVariable Long diaryId){
        return diaryLikeService.getId(memberId, diaryId) != null;
    }

    @DeleteMapping("delete/{memberId}/{diaryId}")
    public void delete(@PathVariable Long memberId, @PathVariable Long diaryId){
        diaryLikeService.removeLike(diaryLikeService.getId(memberId, diaryId));
    }

    @GetMapping("count/{id}")
    public Long getLikeCount(@PathVariable Long id){
        return diaryLikeService.getLikeCount(id);
    }
}
