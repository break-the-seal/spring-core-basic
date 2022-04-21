package io.brick.springcorebasic.scan

import io.brick.springcorebasic.config.AutoAppConfig
import io.brick.springcorebasic.member.MemberService
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest {

    @Test
    fun basicScan() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java)
        val memberService = ac.getBean(MemberService::class.java)

        assertInstanceOf(MemberService::class.java, memberService)
    }
}