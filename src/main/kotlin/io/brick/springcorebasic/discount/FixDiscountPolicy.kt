package io.brick.springcorebasic.discount

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member

class FixDiscountPolicy: DiscountPolicy {
    private val discountFixAmount = 1000

    override fun discount(member: Member, price: Int): Int {
        return if (member.grade == Grade.VIP) {
            discountFixAmount
        } else {
            0
        }
    }
}