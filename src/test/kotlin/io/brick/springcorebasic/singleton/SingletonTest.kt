package io.brick.springcorebasic.singleton

import io.brick.springcorebasic.config.AppConfig
import io.brick.springcorebasic.member.MemberService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    fun pureContainer() {
        val appConfig = AppConfig()

        // 1. 조회: 호출할 때마다 객체를 새로 생성
        val memberService1 = appConfig.memberService()

        // 2. 조회: 호출할 때마다 객체를 새로 생성
        val memberService2 = appConfig.memberService()

        println("memberService1 = ${memberService1}")
        println("memberService2 = ${memberService2}")

        // memberService1 != memberService2
        assertNotSame(memberService1, memberService2)
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    fun singletonServiceTest() {
        val singletonService1 = SingletonService.getInstance()
        val singletonService2 = SingletonService.getInstance()

        assertSame(singletonService1, singletonService2)
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    fun springContainer() {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService1 = ac.getBean("memberService", MemberService::class.java)
        val memberService2 = ac.getBean("memberService", MemberService::class.java)

        println("memberService1 = ${memberService1}")
        println("memberService2 = ${memberService2}")

        // memberService1 == memberService2
        assertSame(memberService1, memberService2)
    }
}