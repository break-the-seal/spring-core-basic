package io.brick.springcorebasic

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import io.brick.springcorebasic.order.OrderService
import io.brick.springcorebasic.order.OrderServiceImpl

class OrderApp {
}

fun main(args: Array<String>) {
    val memberService: MemberService = MemberServiceImpl()
    val orderService: OrderService = OrderServiceImpl()

    val memberId = 1L
    val member = Member(
        id = memberId,
        name = "memberA",
        grade = Grade.VIP
    )

    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)
    println("order = ${order}")
}