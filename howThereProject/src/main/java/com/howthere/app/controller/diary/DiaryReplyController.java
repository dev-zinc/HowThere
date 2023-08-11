package com.howthere.app.controller.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.service.diary.reply.DiaryReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/diary-replies/*")
public class DiaryReplyController {
    private final DiaryReplyService diaryReplyService;

    @PostMapping("write")
    public void write(@RequestBody DiaryReplyDTO diaryReplyDTO){
//        diaryReplyDTO.setMemberId(1L);
        diaryReplyService.write(diaryReplyDTO);
    }

    @GetMapping("list/{id}")
    public Slice<DiaryReplyDTO> list(@PageableDefault(page = 0, size = 5) Pageable pageable, @PathVariable Long id) {
        Slice<DiaryReplyDTO> diaryReplies = diaryReplyService.getListBySlice(pageable, id);
        return diaryReplies;
    }

    @PutMapping("modify")
    public void modify(@RequestBody DiaryReplyDTO diaryReplyDTO){
        diaryReplyService.update(diaryReplyDTO);
    }

    @DeleteMapping("delete/{id}")
    public void remove(@PathVariable Long id){
        diaryReplyService.remove(id);
    }
}
