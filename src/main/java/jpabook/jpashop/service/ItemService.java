package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional  // readOnly 안하도록
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, Book param) {
        Item findItem = itemRepository.findOne(itemId); // 영속 상태
        findItem.setPrice(param.getPrice());    // 값 세팅한 다음에
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
        // 이후 아무것도 호출할 필요가 없다.
        // 스프링의 @Transactional에 의해 트랜잭션이 커밋이 됨
        // 커밋이 되면 JPA가 flush를 날림 (변경점을 다 찾고 그에 대해 업데이트 쿼리를 알아서 날림)
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
