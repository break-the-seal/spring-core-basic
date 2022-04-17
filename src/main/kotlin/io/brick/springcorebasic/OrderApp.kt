package io.brick.springcorebasic

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member

class OrderApp {}

fun main(args: Array<String>) {
    val appConfig = AppConfig()
    val memberService = appConfig.memberService()
    val orderService = appConfig.orderService()

    val memberId: Long = 1L
    val member = Member(memberId, "member-A", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "item-A", 10000)
    println("order = $order")
}