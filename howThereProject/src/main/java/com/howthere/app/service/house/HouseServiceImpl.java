package com.howthere.app.service.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.file.house.HouseFileRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseFileRepository fileRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<HouseDTO> getHouses(Pageable pageable, String keyword) {
        return houseRepository.findWithLimitAndKeyword(pageable, keyword);
    }

    @Override
    public void deleteAllBy(List<Long> ids) {
        houseRepository.deleteAllById(ids);
    }

    @Override
    public House registerHouse(HttpServletRequest req) {
        final String id = req.getParameter("houseId");
        final String houseTitle = req.getParameter("houseTitle");
        final String address = req.getParameter("address");
        final String addressDetail = req.getParameter("addressDetail");
        final String houseContent = req.getParameter("content");
        final Integer guestCnt = Integer.parseInt(req.getParameter("maxGuestCnt"));
        final Integer petCnt = Integer.parseInt(req.getParameter("maxPetCnt"));
        final Double lat = Double.parseDouble(req.getParameter("latitude"));
        final Double lon = Double.parseDouble(req.getParameter("longitude"));

        // TODO: 2023/08/19 로그인 작업 완료 시 주석 해제 후 Member test 제거
//        final Member member = (Member) req.getSession().getAttribute("member");
        final Member test = memberRepository.findById(1L).get(); // 추후 삭제 예정
        final HouseDTO houseDTO = HouseDTO.builder()
            .houseTitle(houseTitle)
            .houseContent(houseContent)
            .houseMaxHeadCount(guestCnt)
            .houseMaxPetCount(petCnt)
            .houseAddress(address)
            .houseAddressDetail(addressDetail)
            .lat(lat)
            .lon(lon)
            .build();
        if (!StringUtils.isEmpty(id)) {
            houseDTO.setId(Long.parseLong(id));
        }
        return houseRepository.save(toEntity(houseDTO, test));
    }

    @Override
    public HouseDTO getHouse(Long id) {
        final HouseDTO houseDTO = houseRepository.getHouse(id);
        final List<String> filePathList = fileRepository.findByHouseIdAndThumb(houseDTO.getId(),
                false)
            .stream()
            .map(file -> file.getFilePath() + "/" + file.getFileUuid())
            .collect(Collectors.toList());
        houseDTO.setFileList(filePathList);
        return houseDTO;
    }

    @Override
    public Page<HouseDTO> getMyHouses(Pageable pageable, Long id) {
        return houseRepository.findAllByIdWithPaging(pageable, id);
    }
}
