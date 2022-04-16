package io.brick.springcorebasic.member

interface MemberService {
    fun join(member: Member)

    fun findMember(memberId: Long): Member?
}