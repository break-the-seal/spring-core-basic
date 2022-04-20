package io.brick.springcorebasic.singleton

import io.brick.springcorebasic.AppConfig
import io.brick.springcorebasic.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    fun pureContainer() {
        val appConfig = AppConfig()

        // 여러개의 조회 -> 요청 개수만큼 객체 생성
        val memberService1 = appConfig.memberService()
        val memberService2 = appConfig.memberService()

        // 참조값이 다름
        println("memberService1 = ${memberService1}")
        println("memberService2 = ${memberService2}")

        Assertions.assertThat(memberService1).isNotSameAs(memberService2)
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    fun singletonService() {
        val singletonService1 = SingletonService.getInstance()
        val singletonService2 = SingletonService.getInstance()

        // 참조값이 다름
        println("singletonService1 = ${singletonService1}")
        println("singletonService2 = ${singletonService2}")

        Assertions.assertThat(singletonService1).isSameAs(singletonService2)
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    fun springContainer() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        // 여러개의 조회 -> 요청 개수만큼 객체 생성
        val memberService1 = ac.getBean("memberService", MemberService::class.java)
        val memberService2 = ac.getBean("memberService", MemberService::class.java)

        // 참조값이 다름
        println("memberService1 = $memberService1")
        println("memberService2 = $memberService2")

        Assertions.assertThat(memberService1).isSameAs(memberService2)
    }
}