package io.brick.springcorebasic.order

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.member.MemberRepository

class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order? {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}