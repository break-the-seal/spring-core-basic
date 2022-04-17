package io.brick.springcorebasic.order

import io.brick.springcorebasic.AppConfig
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {

    private lateinit var memberService: MemberService
    private lateinit var orderService: OrderService

    @BeforeEach
    fun beforeEach() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }

    @Test
    fun createOrder() {
        val memberId: Long = 1L
        val member = Member(memberId, "member-A", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "item-A", 10000)
        Assertions.assertThat(order?.discountPrice).isEqualTo(1000)
    }
}