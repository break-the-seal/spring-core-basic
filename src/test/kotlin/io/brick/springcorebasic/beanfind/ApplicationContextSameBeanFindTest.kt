package io.brick.springcorebasic.beanfind

import io.brick.springcorebasic.member.MemberRepository
import io.brick.springcorebasic.member.MemoryMemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest {
    val ac = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    fun findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException::class.java) {
             val memberRepository = ac.getBean(MemberRepository::class.java)
        }
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    fun findBeanByName() {
        val memberRepository = ac.getBean("memberRepository1", MemberRepository::class.java)
        assertThat(memberRepository).isInstanceOf(MemberRepository::class.java)
    }

    @Test
    @DisplayName("특정 타입을 모두 조회한다.")
    fun findAllBeansByType() {
        val beansOfType = ac.getBeansOfType(MemberRepository::class.java)
        beansOfType.keys.forEach {
            println("key = ${it}, value = ${beansOfType[it]}")
        }

        println("beansOfType = ${beansOfType}")
        assertEquals(beansOfType.size, 2)
    }
    
    @Configuration
    private class SameBeanConfig {
        @Bean
        fun memberRepository1(): MemoryMemberRepository {
            return MemoryMemberRepository()
        }

        @Bean
        fun memberRepository2(): MemoryMemberRepository {
            return MemoryMemberRepository()
        }
    }
}