package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)     // 읽기에는 보통 readonly true
@RequiredArgsConstructor    // 생성자 주입 해줌
public class MemberService {

    // 컴파일 시점에 체크할 수 있기 때문에 final 추가 (생성자에 세팅 안하면 에러 표시됨)
    private final MemberRepository memberRepository;

    /* 필드 주입
    @Autowired MemberRepository memberRepository;
     */

    /* 생성자 주입 (생성자가 하나만 있다면 생략 가능)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 회원 가입
     */
    @Transactional  // 우선적으로 적용되므로 readOnly false (쓰기 이므로)
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
