package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.domain.admin.AnnouncementDetailDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.file.AnnounceFile;
import com.howthere.app.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnnouncementService {
//    조회
    AnnouncementDTO getAnnouncementById(Long id);

    AnnouncementDetailDTO getDetailById(Long id);

//    저장, 수정
    void save(AnnouncementDetailDTO announcementDetailDTO);

    //    삭제
    void remove(AnnouncementDTO announcementDTO);

    //수정
    void modify(AnnouncementDetailDTO announcementDetailDTO);

    //삭제
    void delete(Long id);

//    리스트 가져오기(페이징 처리)
    Page<AnnouncementDTO> getAnnouncementList(Pageable pageable, String keyword);

//    DTO로 변환
    default AnnouncementDTO toDTO(Announcement announcement){
        return AnnouncementDTO.builder()
                .id(announcement.getId())
                .announcementTitle(announcement.getAnnouncementTitle())
                .announcementContent(announcement.getAnnouncementContent())
                .createdDate(announcement.getCreatedDate())
                .updatedDate(announcement.getUpdatedDate())
                .build();
    }

    default AnnouncementDetailDTO toDTO(Announcement announcement, AnnounceFile announceFile){
        AnnouncementDetailDTO.AnnouncementDetailDTOBuilder builder =
                AnnouncementDetailDTO
                        .builder()
                        .id(announcement.getId())
                        .announcementTitle(announcement.getAnnouncementTitle())
                        .announcementContent(announcement.getAnnouncementContent())
                        .createdDate(announcement.getCreatedDate())
                        .updatedDate(announcement.getUpdatedDate())
                        .adminId(announcement.getAdmin().getId());
        return announceFile != null
                ? builder
                    .fileUuid(announceFile.getFileUuid())
                    .fileName(announceFile.getFileName())
                    .filePath(announceFile.getFilePath())
                    .fileSize(announceFile.getFileSize())
                    .build()
                : builder
                    .build();
    }

//    Entity로 변환
    default Announcement toEntity(AnnouncementDTO announcementDTO){
        return Announcement.builder()
                .id(announcementDTO.getId())
                .announcementTitle(announcementDTO.getAnnouncementTitle())
                .announcementContent(announcementDTO.getAnnouncementContent())
                .build();
    }

    default Announcement toEntity(AnnouncementDetailDTO announcementDetailDTO, MemberRepository memberRepository){
        return Announcement.builder()
                .announcementTitle(announcementDetailDTO.getAnnouncementTitle())
                .announcementContent(announcementDetailDTO.getAnnouncementContent())
                .admin(memberRepository.findById(announcementDetailDTO.getAdminId()).orElseThrow(RuntimeException::new))
                .build();
    }


    void deleteAllBy(List<Long> ids);
}
