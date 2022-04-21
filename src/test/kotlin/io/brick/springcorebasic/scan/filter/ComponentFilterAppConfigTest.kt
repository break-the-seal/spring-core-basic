package io.brick.springcorebasic.scan.filter

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

class ComponentFilterAppConfigTest {

    @Test
    fun filterScan() {
        val ac = AnnotationConfigApplicationContext(ComponentFilterAppConfig::class.java)
        val beanA = ac.getBean("beanA", BeanA::class)

        assertNotNull(beanA)

        assertThrows(NoSuchBeanDefinitionException::class.java) {
            ac.getBean("beanB", BeanB::class)
        }
    }

    @Configuration
    @ComponentScan(
        includeFilters = [Filter(type = FilterType.ANNOTATION, classes = [MyIncludeComponent::class])],
        excludeFilters = [Filter(type = FilterType.ANNOTATION, classes = [MyExcludeComponent::class])]
    )
    private class ComponentFilterAppConfig
}