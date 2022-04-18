package io.brick.springcorebasic.member

import io.brick.springcorebasic.config.AppConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberServiceTest {

    lateinit var memberService: MemberService

    @BeforeEach
    fun setUp() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
    }

    @Test
    fun join() {
        // given
        val member = Member(1L, "memberA", Grade.VIP)

        // when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // then
        assertEquals(member, findMember)
    }
}