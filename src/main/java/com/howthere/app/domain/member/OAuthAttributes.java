package com.howthere.app.domain.member;

import com.howthere.app.entity.member.Member;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Slf4j
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
//    private final String mobile;
    private final LocalDate birthDate;
    private final String profile;
    private final LoginType loginType;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//        userNameAttributeName은 .yml에 등록되어 있는 user-name-attribute 값이다.
        log.info("========================{}", userNameAttributeName);

//        registrationId는 네이버 로그인일 경우 naver이고, 카카오 로그인일 경우 kakao이다.
        log.info("========================{}", registrationId);
        if("naver".equals(registrationId)){
            return ofNaver(userNameAttributeName, attributes);
        }else if("google".equals(registrationId)){
            return ofGoogle(userNameAttributeName, attributes);
        }
        return ofKaKao(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKaKao(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get(userNameAttributeName);
        Map profile = (Map) kakaoAccount.get("profile");
        log.info("***************************");
        log.info(kakaoAccount.toString());
//        카카오는 생일을 가져올 수 없어서 생일 뺌
        return OAuthAttributes.builder()
                .email((String)kakaoAccount.get("email"))
                .name((String)profile.get("nickname"))
                .nameAttributeKey(userNameAttributeName)
                .profile((String)profile.get("thumbnail_image_url"))
                .loginType(LoginType.KAKAO)
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> naverAccount = (Map<String, Object>)attributes.get(userNameAttributeName);
        log.info("***************************");
        log.info(naverAccount.toString());
        String birthDay = naverAccount.get("birthyear").toString() + '-' + naverAccount.get("birthday").toString();
        return OAuthAttributes.builder()
                .email((String)naverAccount.get("email"))
                .name((String)naverAccount.get("name"))
                .nameAttributeKey(userNameAttributeName)
                .birthDate(LocalDate.parse(birthDay, DateTimeFormatter.ISO_DATE) )
                .profile((String)naverAccount.get("profile_image"))
                .loginType(LoginType.NAVER)
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        log.info("***************************");
        log.info(attributes.toString());
        return OAuthAttributes.builder()
                .email((String)attributes.get("email"))
                .name((String)attributes.get("name"))
                .nameAttributeKey(userNameAttributeName)
                .birthDate(null)
                .profile((String)attributes.get("picture"))
                .loginType(LoginType.GOOGLE)
                .attributes(attributes)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .memberName(name)
                .memberEmail(email)
                .memberBirthDate(birthDate)
                .memberProfile(profile)
                .memberLoginType(loginType)
                .memberType(MemberType.MEMBER)
                .build();
    }
}
















