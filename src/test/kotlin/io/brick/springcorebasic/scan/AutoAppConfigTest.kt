package io.brick.springcorebasic.scan

import io.brick.springcorebasic.AutoAppConfig
import io.brick.springcorebasic.member.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest {

    @Test
    fun basicScan() {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java)
        val memberService = ac.getBean(MemberService::class.java)

        Assertions.assertThat(memberService).isInstanceOf(MemberService::class.java)
    }
}