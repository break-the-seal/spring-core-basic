package io.brick.springcorebasic.singleton

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class StatefulServiceTest {
    @Test
    fun statefulServiceSingleton() {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService1 = ac.getBean("statefulService", StatefulService::class.java)
        val statefulService2 = ac.getBean("statefulService", StatefulService::class.java)

        statefulService1.order("userA", 10000)
        statefulService2.order("userB", 20000)

        val price = statefulService1.getPrice()
        assertEquals(price, 20000) // A는 10000원 주문했는데 20000원이 주문됨 (동시성 이슈)
    }

    @Configuration
    private class TestConfig {
        @Bean
        fun statefulService(): StatefulService {
            return StatefulService()
        }
    }
}