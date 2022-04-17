package io.brick.springcorebasic.discount

import io.brick.springcorebasic.member.Member

interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    fun discount(member: Member?, price: Int): Int
}