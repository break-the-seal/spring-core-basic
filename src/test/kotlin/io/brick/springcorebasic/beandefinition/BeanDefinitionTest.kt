package io.brick.springcorebasic.beandefinition

import io.brick.springcorebasic.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class BeanDefinitionTest {

    private val ac: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    fun findApplicationBean() {
        val beanDefinitionNames = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames) {
            val beanDefinition = ac.getBeanDefinition(beanDefinitionName)

            if(beanDefinition.role == BeanDefinition.ROLE_APPLICATION) {
                println("beanDefinitionName = ${beanDefinitionName}, beanDefinition = $beanDefinition")
            }
        }
    }
}