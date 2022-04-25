package io.brick.springcorebasic.discount

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import org.springframework.stereotype.Component

@Component
class FixDiscountPolicy : DiscountPolicy {
    companion object {
        private const val DISCOUNT_FIX_AMOUNT = 1000
    }

    override fun discount(member: Member, price: Int): Int {
        return if (member.grade == Grade.VIP) {
            DISCOUNT_FIX_AMOUNT
        } else {
            0
        }
    }
}