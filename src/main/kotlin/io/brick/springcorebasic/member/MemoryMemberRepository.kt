package io.brick.springcorebasic.member

class MemoryMemberRepository : MemberRepository {

    companion object {
        val store = HashMap<Long, Member>()
    }

    override fun save(member: Member) {
        store.put(member.id, member)
    }

    override fun findById(memberId: Long): Member? {
        return store.get(memberId)
    }
}