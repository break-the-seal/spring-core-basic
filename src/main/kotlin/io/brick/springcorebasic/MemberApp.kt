package io.brick.springcorebasic

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberServiceImpl

class MemberApp {}

fun main(args: Array<String>) {
    val appConfig = AppConfig()
    val memberService = appConfig.memberService()
    val member = Member(1L, "member-A", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("findMember = ${findMember?.name}")
}