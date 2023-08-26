package com.howthere.app.config;

import com.howthere.app.type.MemberType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private static final String FAVICON_PATH = "/favicon.ico";
    private static final String MAIN_PATH = "/**";
    private static final String ADMIN_PATH = "/admin/**";
    private static final String QUESTION_PATH = "/one_to_one_question/**";
    private static final String ACCOUNT_PATH = "/account/**";
    private static final String DIARY_PATH = "/diary/**";
    private static final String PROGRAM_PATH = "/program/**";
    private static final String HOST_PATH = "/host/**";

    private static final String LOGIN_PAGE = "/member/login";
    private static final String LOGIN_PROCESSING_URL = "/member/login";
    private static final String LOGOUT_PATH = "/member/logout";

    private static final String REMEMBER_ME_TOKEN_KEY = "hava a nice day";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 60 * 60 * 24 * 14;

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;


    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .mvcMatchers(FAVICON_PATH)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin() // Form 로그인 인증 시작
                .loginPage(LOGIN_PAGE) // 로그인 페이지
                .usernameParameter("memberId") // 로그인 시 아이디 파라미터 이름 설정
                .passwordParameter("memberPassword") // 로그인 시 비밀번호 파라미터 이름 설정
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_PATH)) // logout 요청 경로를 가로챈다.
                .logoutSuccessUrl(LOGIN_PAGE) // logout 성공 시 이동할 경로 작성
                .invalidateHttpSession(Boolean.TRUE)
                .and()
                .rememberMe()
                .rememberMeParameter("auto-login")
                .key(REMEMBER_ME_TOKEN_KEY) // 전달한 문자열 값을 사용해서 토큰을 생성한다.
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED) // 토큰의 유효기간 시간 설정
                .userDetailsService(userDetailsService) // 토큰에 담아 놓을 회원 정보를 저장한다.
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(ADMIN_PATH).hasRole(MemberType.ADMIN.name()) //ADMIN 권한이 있는 회원은 접근 가능
                .antMatchers(QUESTION_PATH).hasAnyRole(MemberType.MEMBER.name(), MemberType.ADMIN.name())
                .antMatchers(ACCOUNT_PATH).hasAnyRole(MemberType.MEMBER.name(), MemberType.ADMIN.name())
                .antMatchers(DIARY_PATH).hasAnyRole(MemberType.MEMBER.name(), MemberType.ADMIN.name())
                .antMatchers(PROGRAM_PATH).hasAnyRole(MemberType.MEMBER.name(), MemberType.ADMIN.name())
                .antMatchers(HOST_PATH).hasAnyRole(MemberType.MEMBER.name(), MemberType.ADMIN.name())
                .and()
                .oauth2Login()
                .userInfoEndpoint();

        return http.build();
    }
}












