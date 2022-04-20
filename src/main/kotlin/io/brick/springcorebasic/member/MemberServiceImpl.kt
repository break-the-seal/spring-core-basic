package io.brick.springcorebasic.member

class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {
    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }

    // 테스트 용도
    fun getMemberRepository(): MemberRepository {
        return this.memberRepository
    }
}