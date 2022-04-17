package io.brick.springcorebasic.member

class MemoryMemberRepository : MemberRepository {

    private var store = HashMap<Long, Member>();

    override fun save(member: Member) {
        store.put(member.id, member)
    }

    override fun findById(memberId: Long): Member? {
        return store.get(memberId)
    }
}