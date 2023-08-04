package com.howthere.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class HowThereApplicationTests {

    @Value("${file-root}")
    String test;
    @Test
    void contextLoads() {
        log.info(test);
    }

}
