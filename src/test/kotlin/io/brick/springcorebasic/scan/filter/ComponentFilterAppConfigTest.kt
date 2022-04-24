package io.brick.springcorebasic.scan.filter

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.*
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

class ComponentFilterAppConfigTest {

    @Configuration
    @ComponentScan(
        includeFilters = [Filter(type = FilterType.ANNOTATION, classes = arrayOf(MyIncludeComponent::class))],
        excludeFilters = [Filter(type = FilterType.ANNOTATION, classes = arrayOf(MyExcludeComponent::class))]
    )
    class ComponentFilterAppConfig

    @Test
    fun filterScan() {
        val ac = AnnotationConfigApplicationContext(ComponentFilterAppConfig::class.java)
        val beanA = ac.getBean("beanA", BeanA::class.java)
        Assertions.assertThat(beanA).isNotNull

        // beanB는 component scan의 대상이 아니기 때문에 가져오지 않음
        assertThrows(
            NoSuchBeanDefinitionException::class.java
        ) { ac.getBean("beanB", BeanB::class.java) }
    }
}