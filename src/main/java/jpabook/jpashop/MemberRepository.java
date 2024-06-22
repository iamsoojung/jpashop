package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository     // component scan 대상이 됨
public class MemberRepository {

    @PersistenceContext     /* 스프링이 자동으로 EntityManager를 주입하도록 함 */
    EntityManager em;   // JPA에서 DB와 상호작용하는 데 사용되는 객체 (DB 내 데이터 저장 or 조회 역할)

    // 저장
    public Long save(Member member) {
        em.persist(member);     // member 객체를 DB에 저장
        return member.getId();  // 저장 후에 가급적이면 아이디 정도만 조회 (전체 return 지양)
    }

    // 하나 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
