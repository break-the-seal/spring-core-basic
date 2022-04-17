package io.brick.springcorebasic.beanfind

import io.brick.springcorebasic.member.MemberRepository
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import io.brick.springcorebasic.member.MemoryMemberRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest {

    private val ac = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

    // 같은 타입 생성
    @Configuration
    class SameBeanConfig {
        @Bean
        fun memberRepository1(): MemberRepository = MemoryMemberRepository()

        @Bean
        fun memberRepository2(): MemberRepository = MemoryMemberRepository()
    }

    @Test
    @DisplayName("같은 타입이 둘 이상 존재하면, 중복오류 발생한다")
    fun findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException::class.java) { ac.getBean(MemberRepository::class.java) }
    }

    @Test
    @DisplayName("같은 타입이 둘 이상 존재하면, 빈 이름을 지정한다")
    fun findBeanByNameWhenTypeDuplicate() {
        val memberRepository = ac.getBean("memberRepository1", MemberRepository::class.java)
        assertThat(memberRepository).isInstanceOf(MemberRepository::class.java)
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    fun findAllBeanByType() {
        val beansOfType = ac.getBeansOfType(MemberRepository::class.java)
        for (key in beansOfType.keys) {
            println("key = ${key}, value = ${beansOfType[key]}")
        }
        assertThat(beansOfType.size).isEqualTo(2)
    }
}