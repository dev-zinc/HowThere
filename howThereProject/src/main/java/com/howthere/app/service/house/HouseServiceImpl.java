package com.howthere.app.service.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<HouseDTO> getHouses(Pageable pageable, String keyword) {
        return houseRepository.findWithLimitAndKeyword(pageable, keyword);
    }

    @Override
    public House save(HttpServletRequest req) {
        final String houseTitle = req.getParameter("houseTitle");
        final String address = req.getParameter("address");
        final String addressDetail = req.getParameter("addressDetail");
        final String houseContent = req.getParameter("content");
        final Integer guestCnt = Integer.parseInt(req.getParameter("maxGuestCnt"));
        final Integer petCnt = Integer.parseInt(req.getParameter("maxPetCnt"));
        final Double lat = Double.parseDouble(req.getParameter("latitude"));
        final Double lon = Double.parseDouble(req.getParameter("longitude"));

        // TODO: 2023/08/19 후에 DTO 협의 후 수정
        final Address addressEntity = Address.builder()
                .address(address)
                .addressDetail(addressDetail)
                .latitude(lat)
                .longitude(lon)
                .build();
//        final Member member = (Member) req.getSession().getAttribute("id");

        final House house = House.builder()
                .houseTitle(houseTitle)
                .houseContent(houseContent)
                .houseMaxHeadCount(guestCnt)
                .houseMaxPetCount(petCnt)
                .address(addressEntity)
                .member(memberRepository.findById(1L).get())
                .build();
        return houseRepository.save(house);
    }
}
