package com.howthere.app.service.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface DiaryService {

//    일기 목록 페이징 처리
//    public Page<Diary> getList(Pageable pageable);

//    일기 목록 더보기, 무한 스크롤 처리
    public Slice<DiaryDTO> getListBySlice(Pageable pageable, String keyword, String order);

//    일기 작성
    public void write(DiaryDTO diaryDTO);

//    작성한 일기 id 가져오기
    public Long getDiaryId();

//    일기 조회
    public Optional<Diary> getDiary(Long id);

//    일기 수정
    public void update(DiaryDTO diaryDTO);

//    일기 조회수 증가
    public void updateViewCount(Long id);

//    일기 삭제
    public void remove(Long id);
//    default Diary toEntity(DiaryDTO diaryDTO){
//        return Diary.builder()
//                .diaryTitle(diaryDTO.getDiaryTitle())
//                .diaryContent(diaryDTO.getDiaryContent())
//                .member(diaryDTO.getMemberId())
////                회원, 숙소 어떻게 가져올지 모르겠다.
////                .member(diaryDTO.getMemberId())
//                .build();
//    }
}
