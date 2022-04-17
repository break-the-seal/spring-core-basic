package io.brick.springcorebasic.order

import io.brick.springcorebasic.discount.FixDiscountPolicy
import io.brick.springcorebasic.member.MemoryMemberRepository

class OrderServiceImpl : OrderService {

    private val memberRepository = MemoryMemberRepository()
    private val discountPolicy = FixDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order? {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}