package io.brick.springcorebasic.order

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OrderServiceTest {
    private val memberService: MemberService = MemberServiceImpl()
    private val orderService: OrderService = OrderServiceImpl()

    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)
        assertEquals(order.discountPrice, 1000)
    }
}