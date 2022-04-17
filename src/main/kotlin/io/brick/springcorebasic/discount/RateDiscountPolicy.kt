package io.brick.springcorebasic.discount

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member

class RateDiscountPolicy : DiscountPolicy {

    private val discountPercent = 10

    override fun discount(member: Member?, price: Int): Int {
        if(member?.grade == Grade.VIP) {
            return price * discountPercent / 100
        } else {
            return 0
        }
    }
}