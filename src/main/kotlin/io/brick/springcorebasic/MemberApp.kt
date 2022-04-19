package io.brick.springcorebasic

import io.brick.springcorebasic.config.AppConfig
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
//    val appConfig = AppConfig()
//    val memberService = appConfig.memberService()
    val context: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = context.getBean("memberService", MemberService::class.java)

    val member = Member(
        id = 1L,
        name = "memberA",
        grade = Grade.VIP
    )

    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("find member = ${findMember?.name}")
}