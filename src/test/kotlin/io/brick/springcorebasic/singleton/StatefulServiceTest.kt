package io.brick.springcorebasic.singleton

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

internal class StatefulServiceTest {

    class TestConfig{
        @Bean
        fun statefulService(): StatefulService {
            return StatefulService()
        }
    }

    @Test
    fun statefulServiceSingleton() {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService1 = ac.getBean(StatefulService::class.java)
        val statefulService2 = ac.getBean(StatefulService::class.java)

        // Thread-A: A사용자가 10000원 주문
        statefulService1.order("user-A", 10000)

        // Thread-B: B사용자가 20000원 주문
        statefulService2.order("user-B", 20000)

        // Thread-A: A사용자가 주문금액 조회
        val price = statefulService1.getPrice()

        // then
        assertThat(statefulService1.getPrice()).isEqualTo(20000)

    }
}