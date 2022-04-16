package io.brick.springcorebasic

import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl

fun main(args: Array<String>) {
    val memberService: MemberService = MemberServiceImpl()
    val member = Member(1L, "memberA", Grade.VIP)

    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("find member = ${findMember?.name}")
}