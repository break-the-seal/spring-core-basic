package io.brick.springcorebasic.member

import io.brick.springcorebasic.AppConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberServiceTest() {

    private lateinit var memberService: MemberService

    @BeforeEach
    fun beforeEach() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
    }

    @Test
    fun join() {
        // given
        val member = Member(1L, "member-A", Grade.VIP)

        // when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // then
        Assertions.assertThat(member).isEqualTo(findMember)
    }
}