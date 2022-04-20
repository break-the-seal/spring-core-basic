package io.brick.springcorebasic.beandefinition

import io.brick.springcorebasic.config.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext

class BeanDefinitionTest {
    // config 코드를 이용한 것은 AppConfig가 제공하는 factory method를 이용해서 bean을 생성
//    private val ac = AnnotationConfigApplicationContext(AppConfig::class.java)
    // xml 설정 방식은 직접 bean을 정의하고 생성
    private val ac = GenericXmlApplicationContext("appConfig.xml")

    /**
     * BeanDefinition는 스프링이 다양한 형태의 설정 정보를 추상화해서 사용하는 것 정도만 이해하면 됨
     */
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    fun findApplicationBean() {
        val beanDefinitionNames = ac.beanDefinitionNames
        for (beanDefinitionName in beanDefinitionNames) {
            val beanDefinition = ac.getBeanDefinition(beanDefinitionName)

            if (beanDefinition.role == BeanDefinition.ROLE_APPLICATION) {
                println("beanDefinitionName = ${beanDefinitionName}, beanDefinition = ${beanDefinition}")
            }
        }
    }
}