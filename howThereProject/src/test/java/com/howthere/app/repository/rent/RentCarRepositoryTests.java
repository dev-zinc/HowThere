package com.howthere.app.repository.rent;

import com.howthere.app.HowThereApplication;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.file.RentCarFile;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.rent.company.RentCarCompanyRepository;
import com.howthere.app.repository.rent.file.RentCarFileRepository;
import com.howthere.app.repository.rent.payment.RentCarPaymentRepository;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.type.RentCarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(false)
public class RentCarRepositoryTests {
    @Autowired
    private RentCarCompanyRepository rentCarCompanyRepository;
    @Autowired
    private RentCarRepository rentCarRepository;
    @Autowired
    private RentCarPaymentRepository rentCarPaymentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RentCarFileRepository rentCarFileRepository;


    // 강원 지역 렌트카 회사 등록 및 렌트카 등록
    @Test
    public void rentCarCompany강원_save_test() {
        RentCarCompany rentCarCompany1 = RentCarCompany.builder()
                .rentCarCompanyName("정도인터내셔널㈜")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("강원도 원주시")
                                .addressDetail("소초면 장양리 54")
                                .latitude(37.42104097).longitude(127.97533700)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany1);

        RentCarCompany rentCarCompany2 = RentCarCompany.builder()
                .rentCarCompanyName("현대디씨렌트카㈜")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("강원도 철원군")
                                .addressDetail("동송읍 이평리 840-88")
                                .latitude(38.20662719).longitude(127.2173654)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany2);

        RentCarCompany rentCarCompany3 = RentCarCompany.builder()
                .rentCarCompanyName("로또렌트카㈜")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("강원도 철원군 ")
                                .addressDetail("갈말읍 군탄리 961-3")
                                .latitude(38.14821979).longitude(127.3042103)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany3);


        for (int i = 6; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }

            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile3 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                        .fileName("산타페")
                        .rentCar(rentCar3)
                        .build();
                rentCarFileRepository.save(rentCarFile3);
            }
        }

        for (int i = 0; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }
            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            RentCarFile rentCarFile3 = RentCarFile.builder()
                    .fileUuid(UUID.randomUUID().toString())
                    .fileSize(16L)
                    .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                    .fileName("산타페")
                    .rentCar(rentCar3)
                    .build();
            rentCarFileRepository.save(rentCarFile3);
        }

        for (int i = 6; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j< 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }
            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile3 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                        .fileName("산타페")
                        .rentCar(rentCar3)
                        .build();
                rentCarFileRepository.save(rentCarFile3);
            }
        }
    }


    // 경기 지역 렌트카 회사 등록 및 렌트카 등록
    @Test
    public void rentCarCompany경기_save_test() {
        RentCarCompany rentCarCompany1 = RentCarCompany.builder()
                .rentCarCompanyName("㈜에스엠프리미엄렌트카")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("경기도 수원시")
                                .addressDetail("권선구 평리동 201-2")
                                .latitude(37.24507872).longitude(126.9917407)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany1);

        RentCarCompany rentCarCompany2 = RentCarCompany.builder()
                .rentCarCompanyName("현대디씨렌트카㈜")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("경기도 수원시")
                                .addressDetail("권선구 곡반정동 506-5")
                                .latitude(37.24572482).longitude(127.0344567)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany2);

        RentCarCompany rentCarCompany3 = RentCarCompany.builder()
                .rentCarCompanyName("㈜엠앤씨렌트카")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("경기도 수원시 ")
                                .addressDetail("팔달구 인계동 1024-11")
                                .latitude(37.26596398).longitude(127.0271582)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany3);

        for (int i = 6; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }

            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile3 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                        .fileName("산타페")
                        .rentCar(rentCar3)
                        .build();
                rentCarFileRepository.save(rentCarFile3);
            }
        }

        for (int i = 0; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }
            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            RentCarFile rentCarFile3 = RentCarFile.builder()
                    .fileUuid(UUID.randomUUID().toString())
                    .fileSize(16L)
                    .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                    .fileName("산타페")
                    .rentCar(rentCar3)
                    .build();
            rentCarFileRepository.save(rentCarFile3);
        }

        for (int i = 6; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j< 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }
            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile3 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                        .fileName("산타페")
                        .rentCar(rentCar3)
                        .build();
                rentCarFileRepository.save(rentCarFile3);
            }
        }
    }

    // 경기 지역 렌트카 회사 등록 및 렌트카 등록
    @Test
    public void rentCarCompany경남_save_test() {
        RentCarCompany rentCarCompany1 = RentCarCompany.builder()
                .rentCarCompanyName("(주)블루렌트카")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("경남남도 거제시 ")
                                .addressDetail("상동동 666-13 1층 3호")
                                .latitude(34.87252035).longitude(128.6314707)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany1);

        RentCarCompany rentCarCompany2 = RentCarCompany.builder()
                .rentCarCompanyName("㈜쏘카")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("경남남도 거제시")
                                .addressDetail("고현동 82-5번지")
                                .latitude(34.88570393).longitude(128.6254564)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany2);

        RentCarCompany rentCarCompany3 = RentCarCompany.builder()
                .rentCarCompanyName("㈜케이디렌트카")
                .rentCarCompanyAddress(
                        Address.builder()
                                .address("경남남도 거제시")
                                .addressDetail("장평동 1195-20")
                                .latitude(37.26596398).longitude(127.0271582)
                                .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany3);

        for (int i = 6; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }

            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile3 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                        .fileName("산타페")
                        .rentCar(rentCar3)
                        .build();
                rentCarFileRepository.save(rentCarFile3);
            }
        }

        for (int i = 0; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }
            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            RentCarFile rentCarFile3 = RentCarFile.builder()
                    .fileUuid(UUID.randomUUID().toString())
                    .fileSize(16L)
                    .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                    .fileName("산타페")
                    .rentCar(rentCar3)
                    .build();
            rentCarFileRepository.save(rentCarFile3);
        }

        for (int i = 6; i < 6; i++) {
            RentCar rentCar1 = RentCar.builder()
                    .rentCarCompany(rentCarCompany3)
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar1);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile1 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://file.carisyou.com/upload/2023/03/13/FILE_202303130931074190.png")
                        .fileName("아반떼")
                        .rentCar(rentCar1)
                        .build();
                rentCarFileRepository.save(rentCarFile1);
            }
            RentCar rentCar2 = RentCar.builder()
                    .rentCarCompany(rentCarCompany1)
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar2);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile2 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdnimage.ebn.co.kr/news/201906/news_1560151842_988495_main1.jpg")
                        .fileName("소나타")
                        .rentCar(rentCar2)
                        .build();
                rentCarFileRepository.save(rentCarFile2);
            }
            RentCar rentCar3 = RentCar.builder()
                    .rentCarCompany(rentCarCompany2)
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar3);
            for (int j = 0; j < 3; j++) {
                RentCarFile rentCarFile3 = RentCarFile.builder()
                        .fileUuid(UUID.randomUUID().toString())
                        .fileSize(16L)
                        .filePath("https://cdn.ftoday.co.kr/news/photo/202112/228724_227751_3414.jpg")
                        .fileName("산타페")
                        .rentCar(rentCar3)
                        .build();
                rentCarFileRepository.save(rentCarFile3);
            }
        }
    }

    // 렌트카 예약
    @Test
    public void rentCarPayment_save_test() {
    }

    // 렌트카 조회
    @Test
    public void findRentCarPaymentById_test() {
        log.info(rentCarPaymentRepository.findById(4L).get().toString());
    }

    // 렌트카 취소
    @Test
    public void deleted_rentCarPayment_remove_test() {
        rentCarPaymentRepository.findById(4L).ifPresent(rentCarPaymentRepository::delete);
    }

    // 렌트카 예약 확인 리스트
    @Test
    public void findAllByMemberIdTest() {
        log.info("---------------------------=====================---------------------========================----------");
        rentCarPaymentRepository.findAllByMemberId_queryDSL(2L).forEach(rentCarPayment -> log.info(rentCarPayment.toString()));
    }

    // 렌트카 예약 확인
    @Test
    public void findOneByIdTest() {
        log.info("---------------------------=====================---------------------========================----------");
        log.info(rentCarPaymentRepository.findOneById_queryDSL(21L).get().toString());
    }

    // 렌트카 예약 수정
    @Test
    public void updateRentCarPaymentByRentCar() {
    }

    // 렌트카 클릭시 렌트카와 렌트카 지도 정보
    @Test
    public void findOneByRentCarIdTest() {
        log.info(rentCarCompanyRepository.findOneByRentCarId(34L).toString());
    }

    //========================================================================================================================================

    // 렌트카 상세정보
    @Test
    public void findOneById() {
        log.info(rentCarRepository.findOneById_dsl(41L).get().toString());
    }
}
