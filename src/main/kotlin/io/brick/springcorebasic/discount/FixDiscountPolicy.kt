package io.brick.springcorebasic.discount

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class FixDiscountPolicy : DiscountPolicy {

    private val discountFixAmount = 1000

    override fun discount(member: Member?, price: Int): Int {
        if (member?.grade == Grade.VIP) {
            return discountFixAmount
        } else {
            return 0
        }
    }
}