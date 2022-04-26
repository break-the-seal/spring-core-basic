package io.brick.springcorebasic.lifecycle

import org.junit.jupiter.api.Test
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifeCycleTest {
    @Test
    fun lifeCycleTest() {
        val ac: ConfigurableApplicationContext = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)
        val client = ac.getBean(NetworkClient::class.java)
        val client2 = ac.getBean(NetworkClient2::class.java)
        val client3 = ac.getBean(NetworkClient3::class.java)

        ac.close()
    }

    @Configuration
    private class LifeCycleConfig {
        @Bean
        fun networkClient(): NetworkClient {
            return NetworkClient().apply {
                url = "http://hello-spring.dev"
            }
        }

        @Bean(initMethod = "init", destroyMethod = "close")
        fun networkClient2(): NetworkClient2 {
            return NetworkClient2().apply {
                url = "http://hello-spring.dev"
            }
        }

        @Bean
        fun networkClient3(): NetworkClient3 {
            return NetworkClient3().apply {
                url = "http://hello-spring.dev"
            }
        }
    }
}