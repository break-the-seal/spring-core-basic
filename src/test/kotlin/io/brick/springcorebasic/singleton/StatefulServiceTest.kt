package io.brick.springcorebasic.singleton

import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class StatefulServiceTest {
    @Test
    fun statefulServiceSingleton() {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService = ac.getBean("statefulService", StatefulService::class.java)
    }

    @Configuration
    private class TestConfig {
        @Bean
        fun statefulService(): StatefulService {
            return StatefulService()
        }
    }
}