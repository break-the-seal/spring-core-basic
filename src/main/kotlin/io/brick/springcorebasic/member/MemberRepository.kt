package io.brick.springcorebasic.member

interface MemberRepository {
    fun save(member: Member)

    fun findById(memberId: Long): Member?
}