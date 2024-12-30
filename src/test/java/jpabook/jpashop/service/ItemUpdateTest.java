package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);    // Book 1번 있다고 가정하고 가져옴

        // TX (트랜잭션) 안에서
        book.setName("what~~"); // 값 바꾸고
        // TX commit 되어버리면
        // JPA가 변경본을 찾아서 업데이트 쿼리를 자동으로 생성해서 DB에 반영함
        // 변경 감지 == dirty checking
        // 따라서, 이 메커니즘으로 JPA의 엔티티를 원하는 데이터로 업데이트할 수 있음
    }
}
