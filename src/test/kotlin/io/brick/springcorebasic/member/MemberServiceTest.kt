package io.brick.springcorebasic.member

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private val memberService: MemberService = MemberServiceImpl()

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