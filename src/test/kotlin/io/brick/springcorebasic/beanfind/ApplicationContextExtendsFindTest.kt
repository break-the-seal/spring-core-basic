package io.brick.springcorebasic.beanfind

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.discount.FixDiscountPolicy
import io.brick.springcorebasic.discount.RateDiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest {
    val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

    @Configuration
    private class TestConfig {
        @Bean
        fun rateDiscountPolicy(): DiscountPolicy {
            return RateDiscountPolicy()
        }

        @Bean
        fun fixDiscountPolicy(): DiscountPolicy {
            return FixDiscountPolicy()
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    fun findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException::class.java) {
            ac.getBean(DiscountPolicy::class.java)
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다..")
    fun findBeanByParentTypeBeanName() {
        val discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java)
        assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    fun findBeanBySubType() {
        val rateDiscountPolicy = ac.getBean(RateDiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    fun findAllBeansByParentType() {
        val beansOfType = ac.getBeansOfType(DiscountPolicy::class.java)
        assertEquals(beansOfType.size, 2)

        beansOfType.keys.forEach {
            println("key = ${it}, value = ${beansOfType[it]}")
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    fun findAllBeansByObjectType() {
        val beansOfType = ac.getBeansOfType(Object::class.java)
        beansOfType.keys.forEach {
            println("key = ${it}, value = ${beansOfType[it]}")
        }
    }
}