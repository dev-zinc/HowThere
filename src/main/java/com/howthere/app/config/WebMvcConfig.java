//package com.howthere.app.config;
//
//import com.howthere.app.interceptor.AuthInterceptor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {
//    private final AuthInterceptor authInterceptor;
//
////    사용자의 인증 후 서비스 이용 시 권한을 체크하기 위해 인터셉터를 사용한다.
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor)
//                .addPathPatterns("/announcement/**")
//                .addPathPatterns("/one_to_one_question/**")
//                .addPathPatterns("/diary/**")
//                .addPathPatterns("/diary-likes/**")
//                .addPathPatterns("/diary-replies/**")
//                .addPathPatterns("/files/**")
//                .addPathPatterns("/host/**")
//                .addPathPatterns("/program/**")
//                .addPathPatterns("/account/**")
//                .excludePathPatterns("/member/**");
//    }
//}
