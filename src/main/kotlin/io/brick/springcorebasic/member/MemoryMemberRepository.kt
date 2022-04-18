package io.brick.springcorebasic.member

class MemoryMemberRepository: MemberRepository {
    companion object {
        val store: MutableMap<Long, Member> = mutableMapOf()
    }

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(memberId: Long): Member? {
        return store[memberId]
    }
}