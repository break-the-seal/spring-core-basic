package io.brick.springcorebasic.member

class MemberServiceImpl(
    private val memberRepository: MemberRepository = MemoryMemberRepository()
) : MemberService {
    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }
}