package io.brick.springcorebasic.order

import io.brick.springcorebasic.config.AppConfig
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {
    lateinit var memberService: MemberService
    lateinit var orderService: OrderService

    @BeforeEach
    fun setUp() {
        val appConfig = AppConfig()

        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }

    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)
        assertEquals(order.discountPrice, 1000)
    }
}