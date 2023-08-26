package com.howthere.app.service.member;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.domain.member.MemberInfoDTO;
import com.howthere.app.domain.member.OAuthAttributes;
import com.howthere.app.entity.member.Member;
import com.howthere.app.provider.MemberDetail;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public Page<MemberInfoDTO> getMembers(Pageable pageable, String keyword) {
        return memberRepository.getMemberInfoDTOs(pageable, keyword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return MemberDetail.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .memberProfile(member.getMemberProfile())
                .memberType(member.getMemberType())
                .build();
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOrUpdate(attributes);

        if(member.getId() == null){
            memberRepository.save(member);
        }

        session.setAttribute("member", new MemberDTO(member));
        log.info("==========================================");
        log.info(((MemberDTO)session.getAttribute("member")).getMemberEmail());
        log.info("==========================================");

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getMemberType().getSecurityRole())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    @Override
    @Transactional
    public void modifyAllActivationById(List<Long> ids) {
        memberRepository.findAllById(ids).forEach(member -> member.setDeleted(!member.isDeleted()));
    }

    @Transactional
    public Member saveOrUpdate(OAuthAttributes attributes){
        return memberRepository.findByMemberEmail(attributes.getEmail())
                .orElse(attributes.toEntity());
    }
}
