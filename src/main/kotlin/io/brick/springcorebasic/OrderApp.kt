package io.brick.springcorebasic

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.order.OrderService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class OrderApp {}

fun main(args: Array<String>) {
//    val appConfig = AppConfig()
//    val memberService = appConfig.memberService()
//    val orderService = appConfig.orderService()

    // 더블콜론(::)을 통해 리플렉션 -> 참조하려는 값을 찾음
    val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = ac.getBean("memberService", MemberService::class.java)
    val orderService = ac.getBean("orderService", OrderService::class.java)

    val memberId: Long = 1L
    val member = Member(memberId, "member-A", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "item-A", 10000)
    println("order = $order")
}