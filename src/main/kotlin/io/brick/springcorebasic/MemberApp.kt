package io.brick.springcorebasic

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class MemberApp {}

fun main(args: Array<String>) {
//    val appConfig = AppConfig()
//    val memberService = appConfig.memberService()

    // 더블콜론(::)을 통해 리플렉션 -> 참조하려는 값을 찾음
    val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = ac.getBean("memberService", MemberService::class.java)

    val member = Member(1L, "member-A", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("findMember = ${findMember?.name}")
}