package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.domain.admin.AnnouncementDetailDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.file.AnnounceFile;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.admin.AnnouncementRepository;
import com.howthere.app.repository.file.AnnounceFileRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public AnnouncementDetailDTO getDetailById(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(RuntimeException::new);
        AnnounceFile announceFile = announceFileRepository.findByAnnouncement_Id(id);
        return toDTO(announcement, announceFile);
    }

    @Override
    @Transactional
    public void save(AnnouncementDetailDTO announcementDetailDTO) {
        Announcement announcement = toEntity(announcementDetailDTO, memberRepository);
        announcementRepository.save(announcement);
        if(announcementDetailDTO.getFileName() != null && !announcementDetailDTO.getFileName().isBlank()) {
            AnnounceFile announceFile = announceFileRepository.toEntity(announcement, announcementDetailDTO);
            announceFileRepository.save(announceFile);
        }
    }

    @Override
    public void remove(AnnouncementDTO announcementDTO) {
        announcementRepository.delete(toEntity(announcementDTO));
    }

    @Override
    @Transactional
    public void modify(AnnouncementDetailDTO announcementDetailDTO) {
        announcementRepository.findById(announcementDetailDTO.getId()).ifPresent(announcement -> {
            Member admin = memberRepository.findById(announcementDetailDTO.getAdminId()).orElseThrow(RuntimeException::new);
            announcement.setAdmin(admin);
            announcement.setAnnouncementTitle(announcementDetailDTO.getAnnouncementTitle());
            announcement.setAnnouncementContent(announcementDetailDTO.getAnnouncementContent());

            if(announcementDetailDTO.getFileName() != null && !announcementDetailDTO.getFileName().isBlank()) {
                announceFileRepository.deleteAllByAnnouncement_Id(announcementDetailDTO.getId());
                announceFileRepository.save(announceFileRepository.toEntity(announcement, announcementDetailDTO));
            }
        });
    }

    @Override
    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public void deleteAllBy(List<Long> ids) {
        announcementRepository.deleteAllById(ids);
    }

    @Override
    public Page<AnnouncementDTO> getAnnouncementList(Pageable pageable, String keyword) {

        return announcementRepository.findAllQueryDSL(pageable, keyword);
    }


}
