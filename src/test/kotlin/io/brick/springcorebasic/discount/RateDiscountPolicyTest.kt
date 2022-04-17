package io.brick.springcorebasic.discount

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RateDiscountPolicyTest {

    private val discountPolicy: DiscountPolicy = RateDiscountPolicy()

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    fun vip_o() {
        // given
        val member = Member(1L, "member-A", Grade.VIP)

        // when
        val discount = discountPolicy.discount(member, 10000)

        // then
        assertThat(discount).isEqualTo(1000)
    }

    @Test
    @DisplayName("VIP가 아니면 10% 할인이 적용되지 않는다")
    fun vip_x() {
        // given
        val member = Member(2L, "member-A", Grade.BASIC)

        // when
        val discount = discountPolicy.discount(member, 10000)

        // then
        assertThat(discount).isEqualTo(0)
    }
}