package io.brick.springcorebasic.discount

import io.brick.springcorebasic.annotation.MainDiscountPolicy
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
//@Primary
@MainDiscountPolicy
class RateDiscountPolicy : DiscountPolicy {
    companion object {
        private const val DISCOUNT_PERCENT = 10
    }

    override fun discount(member: Member, price: Int): Int {
        return if (member.grade == Grade.VIP) {
            price * DISCOUNT_PERCENT / 100
        } else {
            0
        }
    }
}