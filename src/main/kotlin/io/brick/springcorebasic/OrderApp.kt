package io.brick.springcorebasic

import io.brick.springcorebasic.config.AppConfig
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.order.OrderService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class OrderApp {
}

fun main(args: Array<String>) {
//    val appConfig = AppConfig()
//    val memberService = appConfig.memberService()
//    val orderService = appConfig.orderService()
    val context: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = context.getBean("memberService", MemberService::class.java)
    val orderService = context.getBean("orderService", OrderService::class.java)

    val memberId = 1L
    val member = Member(
        id = memberId,
        name = "memberA",
        grade = Grade.VIP
    )

    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 12000)
    println("order = ${order}")
}