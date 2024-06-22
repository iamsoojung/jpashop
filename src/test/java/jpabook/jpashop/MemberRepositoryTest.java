package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional  // springframework import 권장
    @Rollback(false)    // 테스트 후 db rollback X, commit.
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);    // == 비교라고 보면 됨, 저장 한 것과 조회한 것이 같을까? --> true
        System.out.println("findMember == member : " + (findMember == member)); // 영속성 컨텍스트에서 식별자가 같으면 같은 엔터티로 인식
        // 1차 캐시라고 불리는 곳에서도 기존에 관리하던 것이 나온 것 -> 다시 SELECT 쿼리도 때리지 X
    }
}