package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.domain.admin.AnnouncementDetailDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.file.AnnounceFile;
import com.howthere.app.repository.admin.AnnouncementRepository;
import com.howthere.app.repository.file.AnnounceFileRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {
    private final MemberRepository memberRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnounceFileRepository announceFileRepository;

    @Override
    public AnnouncementDTO getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(RuntimeException::new);
        return toDTO(announcement);
    }

    @Override
    @Transactional
    public void save(AnnouncementDetailDTO announcementDetailDTO) {
        Announcement announcement = toEntity(announcementDetailDTO, memberRepository);
        announcementRepository.save(announcement);
        AnnounceFile announceFile = AnnounceFile.builder()
                .announcement(announcement)
                .fileName(announcementDetailDTO.getFileName())
                .fileSize(announcementDetailDTO.getFileSize())
                .filePath(announcementDetailDTO.getFilePath())
                .fileUuid(announcementDetailDTO.getFileUuid())
                .build();
        announceFileRepository.save(announceFile);
    }

    @Override
    public void remove(AnnouncementDTO announcementDTO) {
        announcementRepository.delete(toEntity(announcementDTO));
    }

    @Override
    public Page<AnnouncementDTO> getAnnouncementList(Pageable pageable, String keyword) {

        return announcementRepository.findAllQueryDSL(pageable, keyword);
    }
}
