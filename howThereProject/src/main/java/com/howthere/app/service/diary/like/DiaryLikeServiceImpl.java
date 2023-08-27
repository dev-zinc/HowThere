package com.howthere.app.service.diary.like;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.DiaryLikeDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.diary.like.DiaryLikeRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryLikeServiceImpl implements DiaryLikeService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final DiaryLikeRepository diaryLikeRepository;

    @Override
    public void like(DiaryLikeDTO diaryLikeDTO) {
        Member member = memberRepository.findById(diaryLikeDTO.getMemberId()).orElseThrow();
        Diary diary = diaryRepository.findById(diaryLikeDTO.getDiaryId()).orElseThrow();
        diaryLikeRepository.save(toEntity(diaryLikeDTO, member, diary));
    }

    @Override
    public Long getId(Long memberId, Long diaryId) {
        return diaryLikeRepository.findId(memberId, diaryId);
    }

    @Override
    public void removeLike(Long id) {
        diaryLikeRepository.deleteById(id);
    }

    @Override
    public Long getLikeCount(Long id) {
        return diaryLikeRepository.countLike(id);
    }

    @Override
    public void removeByDiaryId(Long diaryId) {
        diaryLikeRepository.deleteByDiaryId(diaryId);
    }

//    public DiaryLike toEntity(DiaryLikeDTO diaryLikeDTO){
//        return DiaryLike.builder()
//                .id(diaryLikeDTO.getId())
//                .member(memberRepository.findById(diaryLikeDTO.getMemberId()).get())
//                .diary(diaryRepository.findById(diaryLikeDTO.getDiaryId()).get())
//                .build();
//    }
}
