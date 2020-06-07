package com.moderndev.smarthome.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
@Disabled
class SmartHomeDataApplicationTest {

    @Test
    void contextLoads() {
    }

}
