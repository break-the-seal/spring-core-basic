package io.brick.springcorebasic.singleton

import io.brick.springcorebasic.AppConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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
}