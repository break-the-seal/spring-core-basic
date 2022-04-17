package io.brick.springcorebasic.member

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private val memberService = MemberServiceImpl()

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