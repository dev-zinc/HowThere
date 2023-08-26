//package com.howthere.app.service.house.impl;
//
//import com.howthere.app.domain.house.HouseDTO;
//import com.howthere.app.embed.Address;
//import com.howthere.app.entity.house.House;
//import com.howthere.app.repository.house.HouseRepository;
//import com.howthere.app.service.house.HouseService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RequiredArgsConstructor
//@Service
//@Slf4j
//class HouseServiceImpl implements HouseService {
//
//    private final HouseRepository houseRepository;
//
//    @Override
//    public House save(HttpServletRequest req) {
//        final String houseTitle = req.getParameter("houseTitle");
//        final String address = req.getParameter("address");
//        final String addressDetail = req.getParameter("addressDetail");
//        final String houseContent = req.getParameter("content");
//        final Integer guestCnt = Integer.parseInt(req.getParameter("maxGuestCnt"));
//        final Integer petCnt = Integer.parseInt(req.getParameter("maxPetCnt"));
//        final Double lat = Double.parseDouble(req.getParameter("latitude"));
//        final Double lon = Double.parseDouble(req.getParameter("longitude"));
//
//        final Address houseAddress = Address.builder()
//                .address(address)
//                .addressDetail(addressDetail)
//                .longitude(lon)
//                .latitude(lat)
//                .build();
//        final HouseDTO houseDTO = HouseDTO.builder()
//                .houseTitle(houseTitle)
//                .houseContent(houseContent)
//                .houseMaxPetCount(guestCnt)
//                .houseMaxPetCount(petCnt)
//                .houseAddress(houseAddress)
//                .build();
//        return null;
//    }
//}
