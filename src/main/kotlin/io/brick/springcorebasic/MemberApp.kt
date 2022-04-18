package io.brick.springcorebasic

import io.brick.springcorebasic.config.AppConfig
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl

fun main(args: Array<String>) {
    val appConfig = AppConfig()

    val memberService = appConfig.memberService()
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