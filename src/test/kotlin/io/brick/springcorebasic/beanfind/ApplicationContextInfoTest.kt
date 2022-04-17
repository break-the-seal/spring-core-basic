package io.brick.springcorebasic.beanfind

import io.brick.springcorebasic.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest {

    private val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("모든 빈 출력")
    fun findAllBean() {
        val beanDefinitionNames: Array<String> = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames) {
            val bean = ac.getBean(beanDefinitionName)
            println("name = ${beanDefinitionName}, object = ${bean}")
        }
    }
}