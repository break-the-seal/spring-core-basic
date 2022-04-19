package io.brick.springcorebasic.beanfind

import io.brick.springcorebasic.config.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest {
    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("모든 빈 출력하기")
    fun findAllBean() {
        val beanDefinitionNames = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames) {
            val bean = ac.getBean(beanDefinitionName)
            println("name = ${beanDefinitionName}, object = ${bean}")
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    fun findApplicationBean() {
        val beanDefinitionNames = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames) {
            val beanDefinition = ac.getBeanDefinition(beanDefinitionName)

            if (beanDefinition.role == BeanDefinition.ROLE_APPLICATION) {
                val bean = ac.getBean(beanDefinitionName)
                println("name = ${beanDefinitionName}, object = ${bean}")
            }
        }
    }
}